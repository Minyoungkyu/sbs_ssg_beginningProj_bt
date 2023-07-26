package com.baseballtonight.util;

public class Message {
	public static String app_starting_msg =
		  Coloring.getCyan("______                    _             _  _          _____                _         _      _   \n")
		+ Coloring.getCyan("| ___ \\                  | |           | || |        |_   _|              (_)       | |    | |  \n")
		+ Coloring.getCyan("| |_/ /  __ _  ___   ___ | |__    __ _ | || |  ____    | |    ___   _ __   _   __ _ | |__  | |_ \n")
		+ Coloring.getCyan("| ___ \\ / _` |/ __| / _ \\| '_ \\  / _` || || | |____|   | |   / _ \\ | '_ \\ | | / _` || '_ \\ | __|\n")
		+ Coloring.getCyan("| |_/ /| (_| |\\__ \\|  __/| |_) || (_| || || |          | |  | (_) || | | || || (_| || | | || |_ \n")
		+ Coloring.getBlue("\\____/  \\__,_||___/ \\___||_.__/  \\__,_||_||_|          \\_/   \\___/ |_| |_||_| \\__, ||_| |_| \\__|\n")
		+ Coloring.getBlue("                                                                               __/ |            \n")
		+ Coloring.getBlue("                                                                              |___/             \n");
	
	public static String main_menu_cmd_ls = 
		  Coloring.getBlue("	<MAIN MENU>\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("경기 예매하기:		reserve \n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("예매내역 조회:		myreserve \n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("경기장 정보:		stadium \n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("내정보 조회:		self \n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("로그아웃:			logout \n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("애플리케이션 종료:	exit \n")
		+"-----------------------------------------";
	
	public static String stadium_info_cmd_ls = 
		 ""
		+"-----------------------------------------\n"
		+ Coloring.getCyan("식음료 정보:		eat\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("경기장 찾기-버스:	bus\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("경기장 찾기-지하철:	subway\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("주차장:			parking\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("구단 게시판:		board\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("다른 경기장 조회:	other\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("메인 메뉴:		main\n")
		+"-----------------------------------------\n";
	
	public static String self_cmd_ls = 
		 ""
		+"-----------------------------------------\n"
		+ Coloring.getCyan("비밀번호 변경:	password\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("응원구단 변경:	team\n")
		+"-----------------------------------------\n"
		+ Coloring.getCyan("메인 메뉴:	main\n")
		+"-----------------------------------------\n";
}