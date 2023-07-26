package com.baseballtonight.controller;

import java.io.IOException;

import com.baseballtonight.service.MainService;
import com.baseballtonight.service.member.SelfService;

public class SelfController {
	public static void cmdRun(String cmd) throws IOException, InterruptedException {
		switch(cmd){
		case "password":
			SelfService.changePassword();
			break;
			
		case "team":
			SelfService.changePrfTean();
			break;
			
		case "main":
			MainService.mainMenu();
			break;
		}
	}
}
