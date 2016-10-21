/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.picture;

import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Service {

	@Autowired
	Dao dao;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    public Long putPicture(InputStream pictureStream) {
        return dao.putPicture(pictureStream);
    }

    public InputStream getPicture(Long id) {
        return dao.getPicture(id);
    }

}
