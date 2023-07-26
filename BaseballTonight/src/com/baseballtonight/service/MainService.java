package com.baseballtonight.service;

import java.io.IOException;

import com.baseballtonight.controller.MainController;
import com.baseballtonight.util.Message;
import com.baseballtonight.util.UserInput;

public class MainService {
	public static void mainMenu() throws IOException, InterruptedException {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(Message.app_starting_msg);
		System.out.println(Message.main_menu_cmd_ls);
		MainController.cmdRun(UserInput.receiveContainedString(MainController.main_cmd_set));
	}
}
