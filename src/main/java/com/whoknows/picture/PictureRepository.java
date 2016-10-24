/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.picture;

import com.whoknows.domain.Picture;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PictureRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long putPicture(InputStream pictureStream) {
        Picture picture = new Picture();
        picture.setString_mb(pictureStream.toString());
        return 0L;
    }

    public InputStream getPicture(Long id) {
        return jdbcTemplate.query(
                "select string_mb from picture where id = ? limit 1",
                ps -> ps.setLong(1, id),
                (rs, row) -> {
                    return rs.getBlob("string_mb").getBinaryStream();
                }).stream().findAny().orElse(null);
    }
}
