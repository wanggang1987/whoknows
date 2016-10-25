package com.whoknows.picture;

import com.whoknows.domain.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PictureRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Picture getPicture(Long id) {
        return jdbcTemplate.query(
                "select * from picture where id = ? limit 1",
                ps -> ps.setLong(1, id),
                (rs, row) -> {
                    Picture picture = new Picture();
                    picture.setId(rs.getLong("id"));
                    picture.setName(rs.getString("name"));
                    picture.setHeight(rs.getInt("height"));
                    picture.setWidth(rs.getInt("width"));
                    picture.setCreate_time(rs.getTimestamp("create_time"));
                    picture.setStream(rs.getBytes("string"));
                    return picture;
                }).stream().findAny().orElse(null);
    }

    public void putPicture(Picture picture) {
        jdbcTemplate.update("insert into picture(name, width, height, stream) values (?, ?, ?, ?) ",
                ps -> {
                    ps.setString(1, picture.getName());
                    ps.setInt(2, picture.getWidth());
                    ps.setInt(3, picture.getHeight());
                    ps.setBytes(4, picture.getStream());
                });
    }
}
