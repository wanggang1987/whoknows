package com.whoknows.hot;

import com.whoknows.domain.TargetType;
import com.whoknows.follow.FollowService;
import com.whoknows.user.UserDetail;
import com.whoknows.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 5;

	@Autowired
	private HotDAO hotDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;

	public List<VipDetail> listHotVip(Integer page) {
		try {
			UserDetail user = userService.currentUser();

			return hotDAO.listHotVip(page, pageSize).parallelStream().map(hotVip -> {
				hotVip.setFollowCount(followService.followCount(hotVip.getUserID(), TargetType.user));
				if (user != null && user.getId() != null) {
					hotVip.setCurrentFollowed(followService.isFollowed(user.getId(), hotVip.getUserID(), TargetType.user));
				}
				return hotVip;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<VipDetail> listHotVip(String key, Integer page) {
		try {
			UserDetail user = userService.currentUser();

			return hotDAO.listHotVip(key, page, pageSize).parallelStream().map(hotVip -> {
				hotVip.setFollowCount(followService.followCount(hotVip.getUserID(), TargetType.user));
				if (user != null && user.getId() != null) {
					hotVip.setCurrentFollowed(followService.isFollowed(user.getId(), hotVip.getUserID(), TargetType.user));
				}
				return hotVip;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<TagDetail> listHotTags(Integer page) {
		try {
			UserDetail user = userService.currentUser();

			return hotDAO.listHotTag(page, pageSize).parallelStream().map(hotTag -> {
				hotTag.setFollowCount(followService.followCount(hotTag.getTagID(), TargetType.tag));
				if (user != null && user.getId() != null) {
					hotTag.setCurrentFollowed(followService.isFollowed(user.getId(), hotTag.getTagID(), TargetType.tag));
				}
				return hotTag;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<TagDetail> listHotTags(String key, Integer page) {
		try {
			UserDetail user = userService.currentUser();

			return hotDAO.listHotTag(key, page, pageSize).parallelStream().map(hotTag -> {
				hotTag.setFollowCount(followService.followCount(hotTag.getTagID(), TargetType.tag));
				if (user != null && user.getId() != null) {
					hotTag.setCurrentFollowed(followService.isFollowed(user.getId(), hotTag.getTagID(), TargetType.tag));
				}
				return hotTag;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public HotRecommend getRecommend() {
		HotRecommend hotIndex = new HotRecommend();
		try {
			hotIndex.setTags(listHotTags(1));
			hotIndex.setVips(listHotVip(1));
			return hotIndex;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
