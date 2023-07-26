package com.baseballtonight.service.member;

import java.io.IOException;
import java.util.HashSet;

import com.baseballtonight.controller.SelfController;
import com.baseballtonight.dao.member.LoginDAO;
import com.baseballtonight.dao.member.MemberDAO;
import com.baseballtonight.dto.Member;
import com.baseballtonight.dto.Team;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.Message;
import com.baseballtonight.util.UserInput;

public class SelfService {
	static MemberDAO dao = new MemberDAO();
	
	//// cmd List
	static HashSet<String> self_cmd = new HashSet<>();
	static {
		self_cmd.add("password");
		self_cmd.add("team");
		self_cmd.add("main");
	}
	////
	
	public static void serviceRun() throws IOException, InterruptedException {
		SelfService.showMe();
	}

	
	
	
	
	//////
	public static void showMe() throws IOException, InterruptedException {
		String info = String.format("\n\n\n\n\n\n\n\n\n\n\n"
			+ Coloring.getGreen("		<나의 정보 페이지 입니다>\n")
			+ "-------------------------------------------------------\n"
			+ "|  아이디: %s  |  응원구단: %s  |  유저번호: %d  |\n"
			+ "-------------------------------------------------------\n",
			Member.getId(), Member.getPrfTeam().name, dao.getMemberPK());
		System.out.println(info);
		System.out.println(Message.self_cmd_ls);
		String cmd = UserInput.receiveContainedString(self_cmd);
		SelfController.cmdRun(cmd);
	}
	

	public static void changePassword() throws IOException, InterruptedException {
		LoginDAO loginDAO = new LoginDAO();
		while(true){
			Coloring.greenOut("현재 비밀번호");
			String input_password = UserInput.receiveNoSpaceString();
			String confirm_password = loginDAO.getUserPasswordByUserId(Member.getId());
			
			// 비밀번호 불일치시 -> continue;
			if(!input_password.equals(confirm_password)){
				Coloring.redOut("비밀번호가 일치하지 않습니다. 다시 입력해주십시오.");
				continue;
			}
			break;
		}
		// 비밀번호 일치시 이후 코드 실행.
		String new_password;
		while(true) {
			// 비밀번호 입력.
			Coloring.greenOut("변경하실 비밀번호를 입력해주십시오.");
			new_password = UserInput.receiveNoSpaceString();
			
			// 비밀번호 확인
			Coloring.greenOut("\n비밀번호 확인을 위해 다시한번 입력해주십시오.");
			String password_confirm = UserInput.receiveNoSpaceString();
			
			// '비밀번호 != 확인_비밀번호'이라면 == 일 때까지 무한 루프.
			if(!new_password.equals(password_confirm)){
				Coloring.redOut("\n비밀번호가 일치하지 않습니다.\n\n\n");
				Thread.sleep(1000);
				continue;
			}
			break;
		}
		Coloring.greenOut("\n정말로 비밀번호를 변경하시겠습니까? (Y/N)");
		if(UserInput.receiveYesOrNo()){
			MemberDAO.updateNewPassword(new_password);
			Coloring.greenOut("\n비밀번호가 변경되었습니다.");
			Thread.sleep(1500);
			SelfService.showMe();
		} else {
			Coloring.purpleOut("비밀번호를 변경하지 못했습니다. 내정보 페이지로 돌아갑니다.");
			Thread.sleep(1000);
			SelfService.showMe();
		}
	}
	public static void changePrfTean() throws InterruptedException, IOException {
		Coloring.greenOut("현재 선호하는 팀: " + Member.getPrfTeam().name);
		Thread.sleep(1000);
		
		System.out.println(Team.team_ls);
		Coloring.greenOut("변경을 원하는 팀의 번호를 선택하여 주십시오.");
		int newPrfTeam_num = UserInput.receiveLimitedRangeNum(1, 10);
		Coloring.greenOut("정말로 응원하는 팀을 변경하시겠습니까? (Y/N)");
		
		if(UserInput.receiveYesOrNo()){
			
			// member class의 필드 변경.
			Member.setPrfTeam(Team.getTeamByTeamNum(newPrfTeam_num));
			
			// DB 변경.
			dao.updateNewPrfTeam(newPrfTeam_num);
			
			Coloring.greenOut("응원 팀변경이 완료되었습니다.");
			Thread.sleep(1000);
			
		} else {
			Coloring.purpleOut("응원 팀을 변경하지 못했습니다. 내정보 페이지로 이동합니다.");
			Thread.sleep(1000);
		}
		
		
	}
	
	

}
