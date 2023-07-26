package com.baseballtonight.dto;

import com.baseballtonight.util.Coloring;

public enum Team {
	SSG(1, "SSG 랜더스", "인천 SSG 랜더스필드"),
	KIWOOM(2, "키움 히어로즈", "고척 스카이돔"),
	LG(3, "LG 트윈스", "잠실야구장"),
	KT(4, "KT wiz", "수원 케이티 위즈 파크"),
	KIA(5, "KIA 타이거즈", "KIA 챔피언스필드"),
	NC(6, "NC 다이노스", "창원 NC 파크"),
	SAMSUNG(7, "삼성 라이온즈", "대구 삼성 라이온즈 파크"),
	LOTTE(8, "롯데 자이언츠", "사직 야구장"),
	DOOSAN(9, "두산 베어스", "서울종합운동장 야구장"),
	HANWHA(10, "한화 이글스", "대전 한화생명 이글스파크");
	public int num;
	public String name;
	public String stadium;
	
	Team(int clubNum, String name, String home){
		this.num = clubNum;
		this.name = name;
		this.stadium = home;
	}
	
	public static String team_ls = 
		  "------------------\n"
		+ Coloring.getCyan("SSG 랜더스: 	1\n")
		+ "------------------\n"
		+ Coloring.getCyan("키움 히어로즈: 	2\n")
		+ "------------------\n"
		+ Coloring.getCyan("LG 트윈스: 	3\n")
		+ "------------------\n"
		+ Coloring.getCyan("KT wiz:		4\n")
		+ "------------------\n"
		+ Coloring.getCyan("KIA 타이거즈: 	5\n")
		+ "------------------\n"
		+ Coloring.getCyan("NC 다이노스: 	6\n")
		+ "------------------\n"
		+ Coloring.getCyan("삼성 라이온즈:	7\n")
		+ "------------------\n"
		+ Coloring.getCyan("롯데 자이언츠:	8\n")
		+ "------------------\n"
		+ Coloring.getCyan("두산 베어스:	9\n")
		+ "------------------\n"
		+ Coloring.getCyan("한화 이글스:	10\n")
		+ "------------------";
	
	public static Team getTeamByTeamNum(int team_num) {
		switch(team_num){
		case 1:
			return SSG;
		case 2:
			return KIWOOM;
		case 3:
			return LG;
		case 4:
			return KT;
		case 5:
			return KIA;
		case 6:
			return NC;
		case 7:
			return SAMSUNG;
		case 8:
			return LOTTE;
		case 9:
			return DOOSAN;
		case 10:
			return HANWHA;
		default:
			return null;
		}
	}
}

