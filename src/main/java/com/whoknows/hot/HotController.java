package com.whoknows.hot;

import com.whoknows.domain.Topic;
import com.whoknows.domain.Vip;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hot")
public class HotController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HotService hotService;

	@RequestMapping(path = "/vip/{page}", method = RequestMethod.GET)
	public ResponseEntity listHotVip(@PathVariable("page") int page) {
		List<Vip> vips = hotService.listHotVip(page);
		if (vips != null) {
			return ResponseEntity.ok(vips);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/topic/{page}", method = RequestMethod.GET)
	public ResponseEntity listHotTopic(@PathVariable("page") int page) {
		List<Topic> topics = hotService.listHotTopic(page);
		if (topics != null) {
			return ResponseEntity.ok(topics);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
