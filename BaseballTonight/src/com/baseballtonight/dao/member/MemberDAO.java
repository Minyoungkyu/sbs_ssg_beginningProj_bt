package com.baseballtonight.dao.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.dto.Member;

public class MemberDAO {
	static DAO dao = new DAO();
	
	
	
	
	
	public int getMemberPK() {
		int memberPK;
		ResultSet rs = dao.select("SELECT id FROM member WHERE user_id = '" + Member.getId() + "'");
		try {
			rs.next();
			memberPK = rs.getInt("id");
			return memberPK;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}





	public static void updateNewPassword(String NewPassword) {
		String updateNewPassword_sql = String.format(
			  "UPDATE member "
			+ "SET user_password = '%s' "
			+ "WHERE user_id = '%s'" ,
			NewPassword, Member.getId());
		
		dao.update(updateNewPassword_sql);
	}





	public void updateNewPrfTeam(int NewPrfTeam_num) {
		String updateNewPrfTeam_sql = String.format(
			  "UPDATE member "
			+ "SET preferred_team_num = '%d' "
			+ "WHERE user_id = '%s'" ,
			NewPrfTeam_num, Member.getId());
		
		dao.update(updateNewPrfTeam_sql);
	}
}







