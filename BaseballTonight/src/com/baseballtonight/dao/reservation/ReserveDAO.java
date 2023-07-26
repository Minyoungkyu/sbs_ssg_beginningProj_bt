package com.baseballtonight.dao.reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.dto.Member;
import com.baseballtonight.service.reservation.ReserveService;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.SQLUtil;

public class ReserveDAO {
	DAO dao = new DAO();

	public HashSet<Integer> showAllGameList() {
		try {
			HashSet<Integer> gameIdSet = new HashSet<>();
			
			String selectAllGames_sql = "SELECT id, name, stadium, dateAndTime, DAYOFWEEK(dateAndTime) As part, home_club_num, opponent_club_num, home_club_num FROM games ORDER BY dateAndTime ASC";
			ResultSet rs = dao.select(selectAllGames_sql);
			System.out
				.println("--------------------------------------------------------------------------------------");
			while(rs.next()) {
				String name = Coloring.cyan + rs.getString("name") + Coloring.exit;
				String stadium = Coloring.yellow + rs.getString(3) + Coloring.exit;
				String dateAndTime = rs.getString(4);
				System.out.printf("게임번호: %d | %-20s | 경기장: %-12s | %s \n", rs.getInt(1), name, stadium, String
					.valueOf(MyreserveDAO.trimDateAndTime(dateAndTime, SQLUtil.getDayOfWeek(rs.getInt("part")))));
				System.out
					.println("--------------------------------------------------------------------------------------");
				gameIdSet.add(rs.getInt(1));
			}
			rs.close();
			return gameIdSet;
		} catch(SQLException e) {
			System.out.println("SQLException!: " + e);
		}
		return null;
	}

	
	
	/**
	 * 
	 * @param gameId: 요일 정보를 가져와서, 주중 or 주말 판단후 가격 출력.
	 */
	public void showSeatList(int gameId) {
		String loadDayOfWeekByGameId_sql = "SELECT DAYOFWEEK(dateAndTime) As part FROM games WHERE id = " + gameId;
		try {
			ResultSet rs = dao.select(loadDayOfWeekByGameId_sql);
			rs.next();
			int[] priceOfSeatType_arr = getPriceOfSeatType(SQLUtil.getDayOfWeek(rs.getInt("part")));
			
			String seatTypeList = Coloring.getPurple(
				"-------------------------------------------\n"
					+ "PREMIUM석: 최고의 자리, 후회없는 경기 직관\n")
				+ getNumberOfAvailableSeat("premium") + " | 가격: " + priceOfSeatType_arr[0] + "\n\n"
				+ Coloring.getYellow(
					"-------------------------------------------\n"
						+ "TABLE석: 편안한 테이블과 함께 입이 즐거운 관람\n")
				+ getNumberOfAvailableSeat("table") + " | 가격: " + priceOfSeatType_arr[1] + "\n\n"
				+ Coloring.getBlue(
					"-------------------------------------------\n"
						+ "BLUE석: 쾌적한 자리, 중앙에서 외야를 조망\n")
				+ getNumberOfAvailableSeat("blue") + " | 가격: " + priceOfSeatType_arr[2] + "\n\n"
				+ Coloring.getRed(
					"-------------------------------------------\n"
						+ "RED석: 일반석, 합리적인 가격에 경기를 관람\n")
				+ getNumberOfAvailableSeat("red") + " | 가격: " + priceOfSeatType_arr[3] + "\n\n"
				+ Coloring.getCyan(
					"-------------------------------------------\n"
						+ "NAVY석: 안락한 위층에서 경기를 한눈에 조망\n")
				+ getNumberOfAvailableSeat("navy") + " | 가격: " + priceOfSeatType_arr[4] + "\n\n"
				+ Coloring.getGreen(
					"-------------------------------------------\n"
						+ "GREEN석: 가족과 함께 경기를 즐기고 홈런볼의 주인이 되세요!\n")
				+ getNumberOfAvailableSeat("green") + " | 가격: " + priceOfSeatType_arr[5] + "\n\n"
				+ "-------------------------------------------\n";
			
			System.out.print(seatTypeList);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public int[] getPriceOfSeatType(String dayOfWeek) {
		String loadPriceData_sql = "SELECT seatType, weekdayPrice, weekendPrice FROM seatType ORDER BY weekdayPrice DESC";
		ResultSet priceData = dao.select(loadPriceData_sql);
		
		int[] price = new int[6]; // Array to return.
		
		String columnNameByDayOfWeek; // column name for query. (weekdayPrice OR weekendPrice).
		
		if( dayOfWeek.equals("토요일") || dayOfWeek.equals("일요일") ) {
			columnNameByDayOfWeek = "weekendPrice";
		} else {
			columnNameByDayOfWeek = "weekdayPrice";
		}
		
		
		try {
			int i = 0;
			// 각 타입마다 순회하면서 가격 정보를 배열에 저장.
			while(priceData.next()) {
				price[i] = priceData.getInt(columnNameByDayOfWeek);
				i++;
			}
			priceData.close();
			return price;
			
		} catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public String getNumberOfAvailableSeat(String seat_type) {
		int number_of_available_seat = new SeatDAO().getNumberOfAvailableSeatBySeatType(ReserveService.gameId,
			seat_type);
		if(number_of_available_seat != 0) {
			return "예매 가능한 좌석: " + number_of_available_seat + "석";
		} else {
			return "예약 가능한 좌석이 없습니다.";
		}
	}

	public void addNewReservation(int game_id, String seatType, int seatBlock, int seat_id) {
		String addReservationSQL = String.format(
			"INSERT INTO reservations (gameID, seatType, seatBlock, userID, seat_id) "
				+ "VALUES (%d, '%s', %d, '%s', %d)",
			game_id, seatType, seatBlock, Member.getId(), seat_id);

		String updateSeatStatus_sql = String.format(
			"UPDATE seats SET `status` = 1 WHERE seat_id = %d;",
			seat_id);

		dao.update(addReservationSQL);
		Statement st = dao.getState();
		try {
			st.executeUpdate(updateSeatStatus_sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
