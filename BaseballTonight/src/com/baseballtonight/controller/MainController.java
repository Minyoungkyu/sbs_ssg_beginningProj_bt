package com.baseballtonight.controller;


import java.io.IOException;
import java.util.HashSet;

import com.baseballtonight.service.MainService;
import com.baseballtonight.service.member.LogoutService;
import com.baseballtonight.service.member.SelfService;
import com.baseballtonight.service.reservation.MyreserveService;
import com.baseballtonight.service.reservation.ReserveService;
import com.baseballtonight.util.Coloring;

public class MainController {
	
	// only receive contained command.
	public static HashSet<String> main_cmd_set = new HashSet<>();
	static{
		main_cmd_set.add("reserve");
		main_cmd_set.add("myreserve");
		main_cmd_set.add("stadium");
		main_cmd_set.add("self");
		main_cmd_set.add("logout");
		main_cmd_set.add("exit");
	}
	
	
	public static void cmdRun(String cmd) throws IOException, InterruptedException {
		
		switch(cmd){
		
		case "reserve":
			ReserveService.serviceRun();
			break;
			
		case "myreserve":
			MyreserveService.serviceRun();
			break;
			
		case "stadium":
			StadiumInfoController.showPrfStadiumInfo();
			break;
			
		case "self":
			SelfService.serviceRun();
			break;
			
		case "logout":
			LogoutService.serviceRun();
			break;
			
		case "exit":
			Coloring.purpleOut("종료되었습니다.");
			System.exit(0);
			break;
		}
		
		// case logic이 끝나면 main 페이지를 다시 실행.
		MainService.mainMenu();
	}
	
}
