package com.whoknows.paper;

import com.whoknows.domain.Paper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/paper")
public class PaperController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PaperService paperService;

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity createPaper(@RequestBody Paper paper) {
		log.info(ToStringBuilder.reflectionToString(paper, ToStringStyle.MULTI_LINE_STYLE));
		if (paperService.createPaper(paper)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getPaper(@PathVariable("id") Long id) {
		log.info("get paper id: {}", id);
		PaperDetail paperDetail = paperService.getPaperDetail(id);
		if (paperDetail != null) {
			return ResponseEntity.ok(paperDetail);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/list/{userId}/{page}", method = RequestMethod.GET)
	public ResponseEntity getPapers(@PathVariable("userId") Long userId, @PathVariable("page") Integer page) {
		PaperListResponse paperListResponse = paperService.getPapers(userId, page);
		if (paperListResponse != null) {
			return ResponseEntity.ok(paperListResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
