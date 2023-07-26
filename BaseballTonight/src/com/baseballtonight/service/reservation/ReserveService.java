package com.baseballtonight.service.reservation;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import com.baseballtonight.dao.reservation.ReserveDAO;
import com.baseballtonight.dto.Member;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.UserInput;

public class ReserveService {
	static ReserveDAO dao = new ReserveDAO();

	// fields
	public static int gameId; // 경기 PK.
	public static String seatType; // 좌석 타입(blue인지, table인지 등..)
	public static int seatBlock; // 좌석 블록.
	public static int seatColumn; // 좌석 열.
	public static int seatRow; // 좌석 행.
	//

	public static void serviceRun() throws IOException, InterruptedException {

		// 응원팀 경기만을 조회했다면 true.
		boolean showOnlyPrfTeamGame = Games.showGameList(dao);

		if(showOnlyPrfTeamGame) {

			// 응원팀 경기만을 조회했을시. -> 달력 날짜를 gameId로 변환하여 초기화.
			gameId = Games.choiceGamebyDay();
		} else {

			// 전체 경기를 조회했을 경우. -> gameId를 직접 선택해서 초기화.
			gameId = Games.choiceGamebyId();
		}

		// 좌석 타입.
		dao.showSeatList(gameId);
		Coloring.greenOut("예매를 원하시는 좌석의 종류를 입력하여 주십시오.");
		seatType = UserInput.receiveSeatType();

		// 좌석 블록 선택하기.
		BlockService.showSeatBlocks(seatType);
		seatBlock = BlockService.choiceSeatBlock(seatType);

		// 인원수 선택.
		Coloring.greenOut("예매하실 인원 수를 선택해주십시오.(1 ~ 10)");
		int theNumber = UserInput.receiveLimitedRangeNum(1, 10);
		for(int i = 1; i<=theNumber; i++){
			// 좌석 선택.
			int seat_id = SeatChoiceService.choiceSeat(i, theNumber);

			// 선택한 예매 정보를 종합하여 update 쿼리로 전달.
			dao.addNewReservation(gameId, seatType, seatBlock, seat_id);
			Thread.sleep(400);
		}
		Coloring.cyanOut("예매가 완료되었습니다. 감사합니다.");
		Thread.sleep(1000);
	}
}

class Games {
	public static HashSet<Integer> gameIdSet;
	public static HashMap<Integer, Integer> game_id_map;
	public static int gameId;

	public static boolean showGameList(ReserveDAO dao) throws IOException, InterruptedException {

		Coloring.greenOut("응원 팀의 경기 일정만을 보시겠습니까? (Y/N)  (N: 전체 경기일정 보기)");
		boolean user_answer = UserInput.receiveYesOrNo();

		// 응원팀 경기만을 출력.
		if(user_answer) {

			Coloring.greenOut("응원 팀: " + Member.getPrfTeam().name + "의 경기 정보 불러오는 중..");
			Thread.sleep(1000);

			// 경기 calendar 출력.
			game_id_map = GameCalendarService.showCalendar();

			System.out.println(Coloring.getPurple("보라색: ") + "응원 팀 홈 경기");

			// 등록된 모든 경기 출력.
		} else {

			Coloring.greenOut("예매 가능한 전체 경기 일정 불러오는 중..");
			Thread.sleep(1000);

			gameIdSet = dao.showAllGameList();

		}
		// 선호하는 팀의 경기 일정만을 본 경우, true return.
		return user_answer;
	}

	public static int choiceGamebyId() {
		System.out.print(Coloring.getGreen(
			"관람을 원하시는 경기를 선택하시고, 해당 경기의 \'게임번호\'를 입력하여 주십시오.\n") + "게임번호:");
		gameId = UserInput.receiveContainedNum(gameIdSet);
		return gameId;
	}

	public static int choiceGamebyDay() {
		System.out.print(Coloring.getGreen(
			"관람을 원하시는 경기의 \'날짜(일)\'를 입력하여 주십시오.\n") + "일:");
		int game_day = UserInput.receiveContainedNum(new HashSet<Integer>(game_id_map.keySet()));
		gameId = game_id_map.get(game_day);
		return gameId;
	}
}
