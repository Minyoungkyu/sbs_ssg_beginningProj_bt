package com.baseballtonight.controller;

import java.io.IOException;

import com.baseballtonight.service.MainService;
import com.baseballtonight.service.member.JoinService;
import com.baseballtonight.service.member.LoginService;

public class GuestController {
	public static void cmdRun(String cmd) throws IOException, InterruptedException {
		switch(cmd){
		case "join":
			JoinService.doJoin();
			break;
			
		case "login":
			LoginService.doLogin();
			
			// 메인 페이지 실행
			MainService.mainMenu();			
			break;
		}
	}
}
