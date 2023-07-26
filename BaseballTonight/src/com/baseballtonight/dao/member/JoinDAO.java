package com.baseballtonight.dao.member;

import java.sql.ResultSet;

import com.baseballtonight.dao.DAO;

public class JoinDAO {
	ResultSet rs;
	DAO dao = new DAO();
	
	public void newJoin(String user_id, String user_password, int prf_team_num) {
		String register_new_member_SQL = String.format("INSERT INTO member (user_id, user_password, preferred_team_num) VALUES ('%s', '%s', %d)", user_id, user_password, prf_team_num);
		dao.update(register_new_member_SQL);
	}
}