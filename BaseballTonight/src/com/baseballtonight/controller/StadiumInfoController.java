package com.baseballtonight.controller;

import java.io.IOException;
import java.util.HashSet;

import com.baseballtonight.dto.Member;
import com.baseballtonight.dto.Team;
import com.baseballtonight.service.MainService;
import com.baseballtonight.service.information.StadiumInfoService;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.Message;
import com.baseballtonight.util.UserInput;

public class StadiumInfoController {
	// 해당 컨트롤러에서 입력 가능한 명령어 리스트.
	static HashSet<String> stadium_info_cmd_set = new HashSet<>();
	static {
		stadium_info_cmd_set.add("eat");
		stadium_info_cmd_set.add("bus");
		stadium_info_cmd_set.add("subway");
		stadium_info_cmd_set.add("parking");
		stadium_info_cmd_set.add("board");
		stadium_info_cmd_set.add("other");
		stadium_info_cmd_set.add("main");
	}

	public static void cmdRun(Team team) throws IOException, InterruptedException {
		// StadiumInfoDAO 객체에 팀 id정보 전달.
		StadiumInfoService.setShowTargetTeam(team);
		
		// 정보 페이지 명령어 리스트 출력과 명령어 입력 받기.
		System.out.println(Message.stadium_info_cmd_ls);
		String user_cmd = UserInput.receiveContainedString(stadium_info_cmd_set);
		
		switch(user_cmd) {
		case "eat":
			StadiumInfoService.showParkFood();
			System.out.println("------------------------------");
			Coloring.greenOut("뒤로가기: b");
			UserInput.receiveRestrictedString("b");
			StadiumInfoController.cmdRun(team);			
			break;
			
		case "bus":
			StadiumInfoService.showParkBus();
			System.out.println("------------------------------");
			Coloring.greenOut("뒤로가기: b");
			UserInput.receiveRestrictedString("b");
			StadiumInfoController.cmdRun(team);
			break;

		case "subway":
			StadiumInfoService.showParkSub();
			System.out.println("------------------------------");
			Coloring.greenOut("뒤로가기: b");
			UserInput.receiveRestrictedString("b");
			StadiumInfoController.cmdRun(team);
			break;

		case "parking":
			StadiumInfoService.showParkParking();
			System.out.println("------------------------------");
			Coloring.greenOut("뒤로가기: b");
			UserInput.receiveRestrictedString("b");
			StadiumInfoController.cmdRun(team);
			break;

		case "board":
			new BoardController(team.num, team.name, Member.getId());
			break;

		case "other":
			System.out.println(Team.team_ls);
			System.out.println("조회를 원하는 구단을 입력하여 주십시오.");
			int prf_team_num = UserInput.receiveLimitedRangeNum(1, 10); // 1에서 10중에만 입력을 받음..
			Team prf_team = Team.getTeamByTeamNum(prf_team_num); //선호팀 번호 -> 선호팀 enum.
			StadiumInfoController.showOtherStadiumInfo(prf_team);
			break;

		case "main":
			MainService.mainMenu();
			break;
		}
	}
	
	// (기본값) 선호하는 팀의 경기장 정보 보기.
	public static void showPrfStadiumInfo() throws IOException, InterruptedException{
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("회원님의 응원 구단인 " + Coloring.getPurple(Member.getPrfTeam().name) + "의 홈구장, '"
			+ Coloring.getYellow(Member.getPrfTeam().stadium) + "'의 정보 페이지 입니다.\n\n");
		StadiumInfoController.cmdRun(Member.getPrfTeam());
	}
	
	// 선호하는 팀이 아닌 다른 구단의 경기장 정보 보기. (other 명령어의 응답 함수)
	public static void showOtherStadiumInfo(Team other_team) throws IOException, InterruptedException{
		Coloring.greenOut(Coloring.getCyan(other_team.name) + "의 홈구장, '"
			+ Coloring.getYellow(other_team.stadium) + "'의 정보 페이지 입니다.");
		StadiumInfoController.cmdRun(other_team);
	}
}
