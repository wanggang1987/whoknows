package com.whoknows.picture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        return ResponseEntity.ok(pictureService.getPicture(id));
    }
}
