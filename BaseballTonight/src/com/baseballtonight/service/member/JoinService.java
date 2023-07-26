package com.baseballtonight.service.member;

import java.io.IOException;

import com.baseballtonight.AppEntering;
import com.baseballtonight.dao.member.JoinDAO;
import com.baseballtonight.dao.member.LoginDAO;
import com.baseballtonight.dto.Team;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.UserInput;

public class JoinService {
	static JoinDAO dao = new JoinDAO();
	
	public static void doJoin() throws IOException, InterruptedException {
		String memId;
		String memPassword;
		int prfTeam_num;
		
		while(true){
			// 아이디 입력.
			Coloring.greenOut("\n회원가입: 아이디");
			memId = UserInput.receiveNoSpaceString();
			
			// 이미 등록된 아이디가 있는지 확인.(DB에서 비밀번호를 가져와서 null이 아니라면 기존 회원으로 판단.)
			LoginDAO dao = new LoginDAO();
			String registeredPassword = dao.getUserPasswordByUserId(memId);
			
			// 계정이 이미 존재하는 경우: 로그인 페이지로 이동하거나 회원가입 재시도.
			// (아이디를 통해서 불러온 password값이 null이 아닌 경우)
			if(registeredPassword != null){
				Coloring.greenOut("\n이미 존재하는 아이디입니다. 해당 아이디로 로그인 할까요?(Y/N) (N: 회원가입 재시도)");
				if(UserInput.receiveYesOrNo()){
					Coloring.purpleOut("\n로그인 페이지로 이동합니다.");
					Thread.sleep(1000);
					LoginService.doLoginSincePassword(memId);
					break;
				} else {
					continue;
				}
			}
			break;
		}
		
		while(true) {
			// 비밀번호 입력.
			Coloring.greenOut("\n회원가입: 비밀번호");
			memPassword = UserInput.receiveNoSpaceString();
			
			
			// 비밀번호 확인
			Coloring.greenOut("\n회원가입: 비밀번호 확인");
			String member_password_confirm = UserInput.receiveNoSpaceString();
			
			
			// '비밀번호 != 비밀번호 확인'이라면 == 일 때까지 무한 루프.
			if(!memPassword.equals(member_password_confirm)){
				Coloring.redOut("\n비밀번호 확인이 일치하지 않습니다. 다시 입력해 주십시오.");
				continue;
			}
			break;
		}
		
		// 응원팀 선택.
		System.out.println("\n회원가입: 응원 팀을 선택하여 주십시오.");
		Thread.sleep(1000);
		
		// 팀 리스트 출력.
		System.out.println(Team.team_ls);
		prfTeam_num = UserInput.receiveLimitedRangeNum(1, 10);

		
		// DB 등록.
		dao.newJoin(memId, memPassword, prfTeam_num);
		
		
		Coloring.greenOut("회원가입이 완료되었습니다. ID: " + memId);
		Thread.sleep(1500);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		AppEntering.enteringAsGuest();
	}
}
