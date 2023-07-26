package com.baseballtonight.dao.reservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.dto.Member;
import com.baseballtonight.service.MainService;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.SQLUtil;
import com.baseballtonight.util.UserInput;

public class CancelDAO {
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	DAO dao = new DAO();

	public void cancelReservation() throws NumberFormatException, IOException, SQLException, InterruptedException {
		System.out.print("\n취소를 원하시는 예매의 예매번호를 입력하여 주십시오.\n>>>");
		int reservationID;
		int seat_id;
		while(true) {
			try {
				reservationID = Integer.parseInt(rd.readLine());
				String cancelTargetReservationSelectSQL = "SELECT reservationID,\n"
					+ " seatType,\n"
					+ " seatBlock,\n"
					+ " userID,\n"
					+ " name,\n"
					+ " stadium,\n"
					+ " dateAndTime,\n"
					+ " DAYOFWEEK(dateAndTime) As part, \n"
					+ " seat_id \n"
					+ " FROM reservations\n"
					+ "JOIN games\n"
					+ "on gameID = id\n"
					+ "WHERE reservationID =" + reservationID;
				ResultSet rs = dao.select(cancelTargetReservationSelectSQL);
				System.out.println();
				rs.next();
				seat_id = rs.getInt("seat_id");
				if(!rs.getString(4).equals(Member.getId())) {
					throw new Exception();
				}
				String gameName = rs.getString(5);
				String stadium = rs.getString(6);
				String DayOfWeek = SQLUtil.getDayOfWeek(rs.getInt(8));
				StringBuilder startWhen = MyreserveDAO.trimDateAndTime(rs.getString(7), DayOfWeek);
				String seatType = rs.getString(2);
				int seatBlock = rs.getInt(3);
				Thread.sleep(1000);
				Coloring.purpleOut(
					  "예매 당일은 취소수수료가 없으며, 예매수수료도 환불됩니다.\n"
					+ "예매 익일 이후로는 티켓금액의 10% 취소수수료 부과되며, 예매수수료도 환불되지 않습니다.\n"
					+ "경기일자 및 좌석변경 등의 부분 취소는 불가능하며, 기존 건 전체 취소후에 재예매하셔야합니다.\n"
					+ "- 취소좌석에 대한 좌석선점은 보장되지 않습니다.-\n");
				Thread.sleep(2000);
				Coloring.greenOut("위 약관을 확인하였으며, 동의합니다. (동의: Y / 비동의: N)");
				while(true){
					if(UserInput.receiveYesOrNo()){
						break;
					} else {
						Coloring.purpleOut("약관의 동의를 얻지못해 예매 취소에 실패했습니다. 메인으로 돌아갑니다.");
						Thread.sleep(1500);
						MainService.mainMenu();
					}
				}
				
				Coloring.purpleOut("정말로 다음의 예매를 취소하시겠습니까? (Y/N)");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("예매번호: " + reservationID + " | ");
				System.out.print(Coloring.cyan + gameName + Coloring.exit);
				System.out.print(" | ");
				System.out.print(Coloring.yellow + stadium + Coloring.exit);
				System.out.print(" | ");
				System.out.print(seatType + "석 " + seatBlock + "블록 ");
				System.out.print(" | ");
				System.out.print(new SeatDAO().getColumnAndRowBySeatId(rs.getInt("seat_id")));
				System.out.print(" | ");
				System.out.print(startWhen);
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
				break;
			} catch(Exception e) {
				Coloring.redOut("해당 번호로 조회한 예매가 존재하지 않습니다. 다시 입력해 주십시오.\n >>>");
			}
		}
		String cancelReservation_sql = "DELETE FROM reservations\n"
			+ "WHERE reservationID = " + reservationID;
		
		String changeSeatStatus_sql = 
			"UPDATE seats SET `status` = '0' WHERE seat_id = " + seat_id;
		
		while(true) {
			System.out.print(">>>");
			String answer = rd.readLine().toLowerCase();
			if(answer.equals("y")) {
				
				dao.update(cancelReservation_sql);
				dao.update(changeSeatStatus_sql);
				
				Coloring.greenOut("예매 취소가 완료되었습니다. 감사합니다.");
				break;
			} else if(answer.equals("n")) {
				Coloring.purpleOut("예매를 취소하지 못했습니다. 메인으로 이동합니다.");
				break;
			} else {
				Coloring.redOut("올바른 명령어가 아닙니다. 다시 입력해 주십시오.");
			}
		}
		Thread.sleep(1500);
		MainService.mainMenu();
	}
}
