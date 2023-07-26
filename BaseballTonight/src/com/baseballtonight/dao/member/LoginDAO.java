package com.baseballtonight.dao.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.baseballtonight.dao.DAO;

public class LoginDAO {
	DAO dao = new DAO();

	/**
	 * 
	 * @param user_id
	 * @return If user data is registered, return user_password, not null.
	 */
	public String getUserPasswordByUserId(String user_id) {
		String select_user_id_SQL = String.format("SELECT user_password FROM member WHERE user_id = '%s';",
			user_id);
		ResultSet rs = dao.select(select_user_id_SQL);
		try {
			rs.next();
			return rs.getString(1);
		} catch(SQLException e) {
			return null;
		}
	}

	public int getPreferredTeamNumById(String user_id) {
		String load_user_id_SQL = String.format("SELECT preferred_team_num FROM member\n"
			+ "WHERE user_id = '%s'", user_id);
		ResultSet rs = dao.select(load_user_id_SQL);
		try {
			rs.next();
			return rs.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}