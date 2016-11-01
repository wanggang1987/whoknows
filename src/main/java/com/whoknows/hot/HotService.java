package com.whoknows.hot;

import java.util.List;
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

	public List<HotVip> listHotVip(String key, Integer page) {
		try {
			return hotDAO.listHotVip(key, page, pageSize);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<HotTag> listHotTags(String key, Integer page) {
		try {
			return hotDAO.listHotTag(key, page, pageSize);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
