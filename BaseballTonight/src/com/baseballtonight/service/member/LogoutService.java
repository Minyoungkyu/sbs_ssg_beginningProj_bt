package com.baseballtonight.service.member;

import java.io.IOException;

import com.baseballtonight.AppEntering;
import com.baseballtonight.dto.Member;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.UserInput;

public class LogoutService {
	public static void serviceRun() throws IOException, InterruptedException {
		Coloring.greenOut("현재 로그인 중인 아이디: " + Member.getId());
		Coloring.greenOut("정말로 로그아웃 하시겠습니까? (Y/N)");
		
		// y or n 입력.
		if(UserInput.receiveYesOrNo()){
			
			// member 데이터 초기화.
			Member.setAll(null, null, 0);
			
			// 출력.
			Coloring.purpleOut("로그아웃 되었습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			Thread.sleep(1500);
			
			// 다시 게스트로 입장.
			AppEntering.enteringAsGuest();
		} else {
			
			// 출력.
			Coloring.purpleOut("로그아웃에 실패하였습니다. 메인페이지로 이동합니다.");
			Thread.sleep(1500);
		}
	}
}
