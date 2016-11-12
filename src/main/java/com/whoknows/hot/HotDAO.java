package com.whoknows.hot;

import com.whoknows.vip.VipDetail;
import com.whoknows.tag.TagDetail;
import com.whoknows.domain.RoleType;
import com.whoknows.utils.CommonFunction;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HotDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<VipDetail> listHotVip(Integer page, int pageSize) {
		return jdbcTemplate.query("select user.* from user "
				+ "left join user_role on user_role.user_id = user.id "
				+ "where user_role.role_id = ( select id from role where role = '" + RoleType.SITE_VIP.toString() + "' limit 1 ) "
				+ "order by rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setInt(1, pageSize);
					ps.setInt(2, (page - 1) * pageSize);
				},
				(rs, row) -> {
					VipDetail vip = new VipDetail();
					vip.setName(CommonFunction.getUserName(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email")));
					vip.setPricture(rs.getString("picture"));
					vip.setUserID(rs.getLong("id"));
					return vip;
				});
	}

	public List<VipDetail> listHotVip(String key, int page, int pageSize) {
		return jdbcTemplate.query("select user.* from user "
				+ "left join user_role on user_role.user_id = user.id "
				+ "where user_role.role_id = ( select id from role where role = '" + RoleType.SITE_VIP.toString() + "' limit 1 ) "
				+ "and ( email like ? "
				+ "or phone like ? "
				+ "or first_name like ? "
				+ "or last_name like ? ) "
				+ "order by rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setString(1, "%" + key + "%");
					ps.setString(2, "%" + key + "%");
					ps.setString(3, "%" + key + "%");
					ps.setString(4, "%" + key + "%");
					ps.setInt(5, pageSize);
					ps.setInt(6, (page - 1) * pageSize);
				},
				(rs, row) -> {
					VipDetail vip = new VipDetail();
					vip.setName(CommonFunction.getUserName(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email")));
					vip.setPricture(rs.getString("picture"));
					vip.setUserID(rs.getLong("id"));
					return vip;
				});
	}

	public List<TagDetail> listHotTag(Integer page, int pageSize) {
		return jdbcTemplate.query("select * from tag "
				+ "order by rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setInt(1, pageSize);
					ps.setInt(2, (page - 1) * pageSize);
				},
				(rs, row) -> {
					TagDetail tag = new TagDetail();
					tag.setPicture(null);
					tag.setTagID(rs.getLong("id"));
					tag.setTagName(rs.getNString("name"));
					return tag;
				});
	}

	public List<TagDetail> listHotTag(String key, Integer page, int pageSize) {
		return jdbcTemplate.query("select * from tag "
				+ "where name like ? "
				+ "order by rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setString(1, "%" + key + "%");
					ps.setInt(2, pageSize);
					ps.setInt(3, (page - 1) * pageSize);
				},
				(rs, row) -> {
					TagDetail tag = new TagDetail();
					tag.setPicture(null);
					tag.setTagID(rs.getLong("id"));
					tag.setTagName(rs.getNString("name"));
					return tag;
				});
	}
}
