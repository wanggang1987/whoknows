package com.whoknows.user;

import com.whoknows.comment.CommentService;
import com.whoknows.domain.ActionType;
import com.whoknows.domain.Reply;
import com.whoknows.domain.Tag;
import com.whoknows.domain.TargetType;
import com.whoknows.domain.Topic;
import com.whoknows.domain.User;
import com.whoknows.follow.FollowService;
import com.whoknows.vip.VipDetail;
import com.whoknows.like.LikeService;
import com.whoknows.mail.AliMailService;
import com.whoknows.mail.RegisterMailInfo;
import com.whoknows.reply.RelpyService;
import com.whoknows.reply.ReplyDetail;
import com.whoknows.search.Paging;
import com.whoknows.topic.TopicDetail;
import com.whoknows.search.TopicResult;
import com.whoknows.token.TokenService;
import com.whoknows.topic.TopicService;
import com.whoknows.utils.CommonFunction;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 20;
	@Value("${domain}")
	private String domain;
	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TopicService topicService;
	@Autowired
	private RelpyService relpyService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private FollowService followService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private AliMailService aliMailService;
	@Autowired
	private TokenService tokenService;

	public UserDetail currentUser() {
		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal() instanceof UserDetail) {
			return (UserDetail) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
		} else {
			return null;
		}
	}

	public UserDetail getUser(Long id) {
		if (id == null) {
			return null;
		}

		try {
			UserDetail userDetail = new UserDetail();
			User user = userRepository.getUserById(id);
			if (user == null) {
				return null;
			}
			userDetail.setUser(user, userRepository.getUserRolesByUserId(id));
			userDetail.setName(CommonFunction.getUserName(user.getFirstName(), user.getLastName(), user.getEmail()));
			return userDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<TopicResult> getUserTopic(Long id) {
		if (id == null) {
			return null;
		}

		try {
			return userRepository.getUserTopic(id).stream().map(topic -> {
				TopicResult topicResult = new TopicResult();
				topicResult.setTopicDetail(new TopicDetail());
				topicResult.getTopicDetail().setTopic(topic);
				return topicResult;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean createUser(User user) {
		if (StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPasswd())) {
			return false;
		}
		try {
			user.setAction(ActionType.pending.name());
			Long id = userRepository.createUser(user);
			String token = tokenService.genToken();
			tokenService.storeToken(id, token);

			String link = domain + "/token/" + id + "/" + token;
			RegisterMailInfo registerMailInfo = new RegisterMailInfo();
			registerMailInfo.setToAddress(user.getEmail());
			registerMailInfo.setTitle("欢迎注册" + appName);
			registerMailInfo.setContent("注册成功,请点击链接激活账号登陆:\n"
					+ "<a href='"+link+"' style='color:#EDF7EC'>激活</a>");
			aliMailService.regester(registerMailInfo);

			log.info("Create user :{} success.", user.getEmail());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean resetPasswd(ResetPasswdMessage request) {
		if (request == null || StringUtils.isEmpty(request.getEmail())
				|| StringUtils.isEmpty(request.getOldPasswd())
				|| StringUtils.isEmpty(request.getNewPasswd())
				|| StringUtils.isEmpty(request.getRepeatNewPasswd())
				|| !StringUtils.equals(request.getNewPasswd(), request.getRepeatNewPasswd())) {
			return false;
		}

		if (!userRepository.validUserByEmailAndPasswd(request.getEmail(), request.getOldPasswd())) {
			return false;
		}

		try {
			userRepository.resetPasswd(request);
			return true;
		} catch (Exception e) {
			log.error("Reset passwd error , username:{}, {}", request.getEmail(), e);
			return false;
		}
	}

	public boolean editUserInfo(UserDetail user) {
		if (user == null || user.getId() == null) {
			return false;
		}

		try {
			userRepository.editUserInfo(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public UserTopicResponse getUserCreateTopics(Long userID, int page) {
		try {
			UserTopicResponse userTopicResponse = new UserTopicResponse();
			Paging paging = new Paging();
			paging.setCurrentPage(page);
			paging.setPerPage(pageSize);
			int commentCount = userRepository.getUserCreateTopicCount(userID);
			paging.setTotalPage(commentCount % pageSize == 0 ? commentCount / pageSize : commentCount / pageSize + 1);
			userTopicResponse.setPaging(paging);

			userTopicResponse.setTopicResults(userRepository.getUserCreateTopics(userID, page, pageSize).parallelStream().map(topic -> {
				TopicResult topicResult = new TopicResult();
				TopicDetail topicDetail = new TopicDetail();
				topicDetail.setTopic(topic);
				topicDetail.setAuthor(getUser(topic.getId()));
				topicDetail.setFollowCount(followService.followCount(topic.getId(), TargetType.topic));
				topicDetail.setCurrentFollowed(followService.isFollowed(userID, topic.getId(), TargetType.topic));
				topicResult.setTopicDetail(topicDetail);

				Reply reply = relpyService.getHotReplyForRopic(topic.getId());
				if (reply != null) {
					ReplyDetail replyDetail = new ReplyDetail();
					replyDetail.setReply(reply);
					replyDetail.setAuthor(getUser(reply.getUser_id()));
					replyDetail.setLikeCount(likeService.likeCount(reply.getId(), TargetType.reply));
					replyDetail.setCommentCount(commentService.commentCount(reply.getId()));
					replyDetail.setCurrentLiked(likeService.isLiked(userID, reply.getId(), TargetType.reply));
					topicResult.setReplyDetail(replyDetail);
				}
				return topicResult;
			}).collect(Collectors.toList()));
			return userTopicResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserTopicResponse getUserFollowTopics(Long userID, int page) {
		try {
			UserTopicResponse userTopicResponse = new UserTopicResponse();
			Paging paging = new Paging();
			paging.setCurrentPage(page);
			paging.setPerPage(pageSize);
			int commentCount = userRepository.getUserFollowTopicCount(userID);
			paging.setTotalPage(commentCount % pageSize == 0 ? commentCount / pageSize : commentCount / pageSize + 1);
			userTopicResponse.setPaging(paging);

			userTopicResponse.setTopicResults(userRepository.getUserFollowTopics(userID, page, pageSize).parallelStream().map(topic -> {
				TopicResult topicResult = new TopicResult();
				TopicDetail topicDetail = new TopicDetail();
				topicDetail.setTopic(topic);
				topicDetail.setAuthor(getUser(topic.getId()));
				topicDetail.setFollowCount(followService.followCount(topic.getId(), TargetType.topic));
				topicDetail.setCurrentFollowed(followService.isFollowed(userID, topic.getId(), TargetType.topic));
				topicResult.setTopicDetail(topicDetail);

				Reply reply = relpyService.getHotReplyForRopic(topic.getId());
				if (reply != null) {
					ReplyDetail replyDetail = new ReplyDetail();
					replyDetail.setReply(reply);
					replyDetail.setAuthor(getUser(reply.getUser_id()));
					replyDetail.setLikeCount(likeService.likeCount(reply.getId(), TargetType.reply));
					replyDetail.setCommentCount(commentService.commentCount(reply.getId()));
					replyDetail.setCurrentLiked(likeService.isLiked(userID, reply.getId(), TargetType.reply));
					topicResult.setReplyDetail(replyDetail);
				}
				return topicResult;
			}).collect(Collectors.toList()));
			return userTopicResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserTopicResponse getUserReplyTopics(Long userID, int page) {
		try {
			UserTopicResponse userTopicResponse = new UserTopicResponse();
			Paging paging = new Paging();
			paging.setCurrentPage(page);
			paging.setPerPage(pageSize);
			int commentCount = userRepository.getUserReplyCount(userID);
			paging.setTotalPage(commentCount % pageSize == 0 ? commentCount / pageSize : commentCount / pageSize + 1);
			userTopicResponse.setPaging(paging);

			userTopicResponse.setTopicResults(userRepository.getUserReplys(userID, page, pageSize).parallelStream().map(reply -> {
				TopicResult topicResult = new TopicResult();
				Topic topic = topicService.getTopic(reply.getTopic_id());
				if (topic != null) {
					TopicDetail topicDetail = new TopicDetail();
					topicDetail.setTopic(topic);
					topicDetail.setAuthor(getUser(topic.getId()));
					topicDetail.setFollowCount(followService.followCount(topic.getId(), TargetType.topic));
					topicDetail.setCurrentFollowed(followService.isFollowed(userID, topic.getId(), TargetType.topic));
					topicResult.setTopicDetail(topicDetail);
				} else {
					return null;
				}

				ReplyDetail replyDetail = new ReplyDetail();
				replyDetail.setReply(reply);
				replyDetail.setAuthor(getUser(reply.getUser_id()));
				replyDetail.setLikeCount(likeService.likeCount(reply.getId(), TargetType.reply));
				replyDetail.setCommentCount(commentService.commentCount(reply.getId()));
				replyDetail.setCurrentLiked(likeService.isLiked(userID, reply.getId(), TargetType.reply));
				topicResult.setReplyDetail(replyDetail);
				return topicResult;
			}).filter(topicResult -> topicResult != null).collect(Collectors.toList()));
			return userTopicResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Tag> getUserFollowTagList() {
		try {
			UserDetail user = currentUser();
			if (user != null && user.getId() != null) {
				return userRepository.getUserFollowTagList(user.getId());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<VipDetail> getUserFollowVipList() {
		try {
			UserDetail user = currentUser();
			if (user != null && user.getId() != null) {
				return userRepository.getUserFollowVipList(user.getId()).parallelStream().map(hotVip -> {
					hotVip.setFollowCount(followService.followCount(hotVip.getUserID(), TargetType.user));
					hotVip.setCurrentFollowed(followService.isFollowed(user.getId(), hotVip.getUserID(), TargetType.user));
					return hotVip;
				}).collect(Collectors.toList());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserConutInfoMessage getUserCountInfo(Long userId) {
		UserConutInfoMessage userConutInfoResponse = new UserConutInfoMessage();
		userConutInfoResponse.setReplyCount(userRepository.getUserReplyCount(userId));
		userConutInfoResponse.setCreateTopicCount(userRepository.getUserCreateTopicCount(userId));
		userConutInfoResponse.setFollowCount(userRepository.getUserFollowTopicCount(userId));
		return userConutInfoResponse;
	}

	public void activeUser(Long userId) {
		try {
			userRepository.activeUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
