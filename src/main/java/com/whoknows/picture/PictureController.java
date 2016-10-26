package com.whoknows.picture;

import com.whoknows.domain.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/picture")
public class PictureController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PictureService pictureService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getPicture(Long id) {
		Picture picture = pictureService.getPicture(id);
		if (picture != null) {
			return ResponseEntity.ok(picture);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity putPicture(@RequestBody Picture picture) {
		if (pictureService.putPicture(picture)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
