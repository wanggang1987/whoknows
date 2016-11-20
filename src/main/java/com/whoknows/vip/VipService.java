package com.whoknows.vip;

import com.whoknows.domain.TargetType;
import com.whoknows.follow.FollowService;
import com.whoknows.user.UserDetail;
import com.whoknows.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;

	public VipDetail getVipDetail(Long userId) {
		if (userId == null) {
			return null;
		}
		try {
			UserDetail userDetail = userService.getUser(userId);
			if (userDetail == null) {
				return null;
			}
			VipDetail vipDetail = new VipDetail();
			vipDetail.setUserDetail(userDetail);
			vipDetail.setFollowCount(followService.followCount(userDetail.getId(), TargetType.user));

			UserDetail user = userService.currentUser();
			if (user != null && user.getId() != null) {
				vipDetail.setCurrentFollowed(followService.isFollowed(user.getId(), userDetail.getId(), TargetType.user));
			}
			return vipDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
