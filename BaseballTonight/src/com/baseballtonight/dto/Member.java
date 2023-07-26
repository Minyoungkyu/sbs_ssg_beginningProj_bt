package com.baseballtonight.dto;

public class Member { // getter로 가져다쓰면 됩니다.
	
	private static String id;
	private static String password;
	private static Team prf_team;
	
	public static void setAll(String mem_id, String mem_password, int prf_team_num){
		Member.id = mem_id;
		Member.password = mem_password;
		Member.prf_team = Team.getTeamByTeamNum(prf_team_num);
	}
	public static String getId() {
		return id;
	}
	public static void setId(String mem_id) {
		 Member.id = mem_id;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String mem_password) {
		Member.password = mem_password;
	}
	public static Team getPrfTeam() {
		return prf_team;
	}
	public static void setPrfTeam(Team prf_team) {
		Member.prf_team = prf_team;
	}
	
	@Override
	public String toString() {
		return "Member [mem_id=" + id + ", mem_password=" + password + ", prf_team=" + prf_team
			+ "]";
	}
}
