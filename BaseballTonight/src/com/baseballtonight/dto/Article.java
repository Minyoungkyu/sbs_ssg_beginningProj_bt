package com.baseballtonight.dto;

public class Article {
	
	public int id;
	public String regDate;
	public String title;
	public String body;
	public String memberId; // memberId 가 String 으로 바뀌어야 함
	public int hit;
	public int recommend;
	
	public Article(int id, String regDate, String title, String body, String memberId, int hit, int recommend) { // memberId 가 String 으로 바뀌어야 함.
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.hit = hit;
		this.recommend = recommend;
	}

}
