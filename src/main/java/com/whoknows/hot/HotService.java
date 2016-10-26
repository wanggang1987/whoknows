package com.whoknows.hot;

import com.whoknows.domain.Topic;
import com.whoknows.domain.Vip;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HotDAO hotDAO;

	public List<Vip> listHotVip(int page) {
		try {
			return hotDAO.listHotVip(page);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<Topic> listHotTopic(int page) {
		try {
			return hotDAO.listHotTopic(page);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
