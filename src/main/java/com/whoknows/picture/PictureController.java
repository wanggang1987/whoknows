package com.whoknows.picture;

import com.whoknows.domain.Picture;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/img")
public class PictureController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PictureService pictureService;

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getPicture(@PathVariable("id") Long id) {
		Picture picture = pictureService.getPicture(id);

		if (picture != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
			return new ResponseEntity<byte[]>(picture.getStream(), headers, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity putPicture(@RequestParam("img") MultipartFile uploadfile) throws IOException {
		Picture picture = new Picture();
		picture.setName(uploadfile.getOriginalFilename());
		picture.setStream(uploadfile.getBytes());

		Long id = pictureService.putPicture(picture);
		if (id != null) {
			return ResponseEntity.ok(id);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
