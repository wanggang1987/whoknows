package com.whoknows.hot;

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
	public ResponseEntity searchVipyKeyWordOnRank(String keyWord, @PathVariable("page") Integer page) {
		List<HotVip> vips = null;
		if (keyWord == null) {
			vips = hotService.listHotVip(page);
		} else {
			vips = hotService.listHotVip(keyWord, page);
		}

		if (vips != null) {
			return ResponseEntity.ok(vips);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/tag/{page}", method = RequestMethod.GET)
	public ResponseEntity listHotTag(String keyWord, @PathVariable("page") Integer page) {
		List<HotTag> topics = null;
		if (keyWord == null) {
			topics = hotService.listHotTags(page);
		} else {
			topics = hotService.listHotTags(keyWord, page);
		}

		if (topics != null) {
			return ResponseEntity.ok(topics);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@RequestMapping(path = "/recommend", method = RequestMethod.GET)
	public ResponseEntity getRecommed()
	{
		HotIndex hotIndex = new HotIndex();
		hotIndex.setTags(hotService.listHotTags(1));
		hotIndex.setVips(hotService.listHotVip(1));
		
		return ResponseEntity.ok(hotIndex);
	}
}
