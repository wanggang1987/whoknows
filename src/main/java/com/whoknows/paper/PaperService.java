package com.whoknows.paper;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.Paper;
import com.whoknows.domain.TargetType;
import com.whoknows.like.LikeService;
import com.whoknows.search.Paging;
import com.whoknows.user.UserDetail;
import com.whoknows.user.UserService;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 3;

	@Autowired
	private PaperRepository paperRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;

	public boolean createPaper(Paper paper) {
		if (paper.getUser_id() == null
				|| StringUtils.isEmpty(paper.getTitle())
				|| StringUtils.isEmpty(paper.getContent())) {
			return false;
		}
		paper.setAction(ActionType.pending.toString());

		try {
			paperRepository.createPaper(paper);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public PaperDetail getPaperDetail(Long id) {
		if (id == null) {
			return null;
		}

		try {
			Paper paper = paperRepository.getPaper(id);
			if (paper == null) {
				return null;
			}

			PaperDetail paperDetail = new PaperDetail();
			paperDetail.setPaper(paper);
			paperDetail.setAuthor(userService.getUser(paper.getUser_id()));
			paperDetail.setLikeCount(likeService.likeCount(paper.getId(), TargetType.paper));
			UserDetail user = userService.currentUser();
			if (user != null && user.getId() != null) {
				paperDetail.setCurrentLiked(likeService.isLiked(user.getId(), paper.getId(), TargetType.paper));
			}
			return paperDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PaperListResponse getPapers(Long userId, int page) {
		if (userId == null) {
			return null;
		}

		try {
			PaperListResponse paperListResponse = new PaperListResponse();
			Paging paging = new Paging();
			paging.setCurrentPage(page);
			paging.setPerPage(pageSize);
			int commentCount = paperRepository.getPaperCount(userId);
			paging.setTotalPage(commentCount % pageSize == 0 ? commentCount / pageSize : commentCount / pageSize + 1);
			paperListResponse.setPaging(paging);

			paperListResponse.setPapers(paperRepository.getPaperList(userId, page, pageSize)
					.stream().map(paperId -> getPaperDetail(paperId))
					.collect(Collectors.toList()));
			return paperListResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
