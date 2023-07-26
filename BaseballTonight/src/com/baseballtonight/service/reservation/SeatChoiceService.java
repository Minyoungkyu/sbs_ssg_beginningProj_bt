package com.baseballtonight.service.reservation;

import java.util.HashSet;

import com.baseballtonight.dao.reservation.SeatDAO;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.UserInput;

public class SeatChoiceService {
	static SeatDAO dao = new SeatDAO();
	
	
	
	
	public static int choiceSeat(int nowNumber, int totalNumber) {
		String seatView = String.format(""
			+ "+===================================+		\n"
			+ "|  	열 |  a열   b열  c열   d열  e열	|		\n"
			+ "|-----------------------------------	|		\n"
			+ "| 1번줄:  [%s]  [%s]  [%s]  [%s]  [%s]		|		\n"
			+ "|									|		\n"
			+ "| 2번줄:  [%s]  [%s]  [%s]  [%s]  [%s]		|		\n"
			+ "|									|		\n"			   
			+ "| 3번줄:  [%s]  [%s]  [%s]  [%s]  [%s]		|		\n"
			+ "|									|		\n"			                                                
			+ "| 4번줄:  [%s]  [%s]  [%s]  [%s]  [%s]		|		\n"
			+ "|									|		\n"			                                 
			+ "| 5번줄:  [%s]  [%s]  [%s]  [%s]  [%s]		|		\n"
			+ "|									|		\n"			                  
			+ "| 6번줄:  [%s]  [%s]  [%s]  [%s]  [%s]		|		\n"               
			+ "|									|		\n"			   
			+ "| 7번줄:  [%s]  [%s]  [%s]  [%s]  [%s]		|		\n"
			+ "+===================================+",
			dao.loadSeatStatusToOX(1, 1),
			dao.loadSeatStatusToOX(1, 2),
			dao.loadSeatStatusToOX(1, 3),
			dao.loadSeatStatusToOX(1, 4),
			dao.loadSeatStatusToOX(1, 5),

			dao.loadSeatStatusToOX(2, 1),
			dao.loadSeatStatusToOX(2, 2),
			dao.loadSeatStatusToOX(2, 3),
			dao.loadSeatStatusToOX(2, 4),
			dao.loadSeatStatusToOX(2, 5),

			dao.loadSeatStatusToOX(3, 1),
			dao.loadSeatStatusToOX(3, 2),
			dao.loadSeatStatusToOX(3, 3),
			dao.loadSeatStatusToOX(3, 4),
			dao.loadSeatStatusToOX(3, 5),

			dao.loadSeatStatusToOX(4, 1),
			dao.loadSeatStatusToOX(4, 2),
			dao.loadSeatStatusToOX(4, 3),
			dao.loadSeatStatusToOX(4, 4),
			dao.loadSeatStatusToOX(4, 5),

			dao.loadSeatStatusToOX(5, 1),
			dao.loadSeatStatusToOX(5, 2),
			dao.loadSeatStatusToOX(5, 3),
			dao.loadSeatStatusToOX(5, 4),
			dao.loadSeatStatusToOX(5, 5),

			dao.loadSeatStatusToOX(6, 1),
			dao.loadSeatStatusToOX(6, 2),
			dao.loadSeatStatusToOX(6, 3),
			dao.loadSeatStatusToOX(6, 4),
			dao.loadSeatStatusToOX(6, 5),

			dao.loadSeatStatusToOX(7, 1),
			dao.loadSeatStatusToOX(7, 2),
			dao.loadSeatStatusToOX(7, 3),
			dao.loadSeatStatusToOX(7, 4),
			dao.loadSeatStatusToOX(7, 5)
			);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(seatView);
		
		
		// ===== 좌석 선택값 입력 로직 ======
		
		// 열(column) 입력 받기.
		HashSet<String> columns = new HashSet<>();
		columns.add("a");
		columns.add("b");
		columns.add("c");
		columns.add("d");
		columns.add("e");
		
		
		int user_column = 0;
		int user_row;
		
		while(true){
			Coloring.cyanOut( "예매 인원 현황: ("+ nowNumber + "/" + totalNumber + ")" );
			Coloring.greenOut("원하시는 좌석의 열을 선택해주세요(a, b, c, d, e)");
			String user_column_string = UserInput.receiveContainedString(columns);
			switch(user_column_string){
			case "a":
				user_column = 1;
				break;
				
			case "b":
				user_column = 2;
				break;
				
			case "c":
				user_column = 3;
				break;
				
			case "d":
				user_column = 4;
				break;
				
			case "e":
				user_column = 5;
				break;
			}
			
			// 행 입력 받기.
			Coloring.greenOut("원하시는 좌석의 행을 선택해주세요(1 ~ 7)");
			user_row = UserInput.receiveLimitedRangeNum(1, 7);
			
			
			
			
			// 예약 가능한 좌석인 경우.
			if(dao.isAvailableSeat(user_row, user_column)){
				// 좌석 고유번호(id) 가져오기.
				int seat_id = dao.getSeatId(user_row, user_column);
				return seat_id;
				
			// 이미 예약된 좌석인 경우.
			} else {
				Coloring.purpleOut("이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.\n");
			}
		}
		
	}

}










