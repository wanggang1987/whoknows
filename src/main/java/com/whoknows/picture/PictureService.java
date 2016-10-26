package com.whoknows.picture;

import com.whoknows.domain.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

	@Autowired
	private PictureRepository pictureRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public Picture getPicture(Long id) {
		if (id == null) {
			return null;
		}

		try {
			return pictureRepository.getPicture(id);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean putPicture(Picture picture) {
		if (picture.getStream() == null) {
			return false;
		}

		try {
			pictureRepository.putPicture(picture);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
}
