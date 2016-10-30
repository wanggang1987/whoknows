package com.whoknows.hot;

import com.whoknows.reply.RelpyService;
import com.whoknows.user.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final int pageSize = 20;

	@Autowired
	private HotDAO hotDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private RelpyService relpyService;

	public List<HotVip> listHotVip(Integer page) {

		try {
			return hotDAO.listHotVip(page, pageSize);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<HotTag> listHotTags(Integer page) {
		try {
			return hotDAO.listHotTag(page, pageSize);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
