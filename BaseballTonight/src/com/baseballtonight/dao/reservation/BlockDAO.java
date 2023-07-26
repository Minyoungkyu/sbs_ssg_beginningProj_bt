package com.baseballtonight.dao.reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.service.reservation.ReserveService;
import com.baseballtonight.util.Coloring;

public class BlockDAO {
	static DAO dao = new DAO();

	public String getAvailableSeatNumOrSoldOutString(int block_num) {
		try {
			String loadAvailableSeatNum_sql = String.format(""
				+ "SELECT COUNT(*) AS availableSeat_num "
				+ "FROM seats "
				+ "WHERE game_id = %d AND seat_block = %d AND status = 0;", ReserveService.gameId, block_num);
			ResultSet rs = dao.select(loadAvailableSeatNum_sql);
			rs.next();

			// 예매 가능한 좌석이 0석이라면 "n석" 대신 "매진" return.
			if(rs.getInt(1) != 0) {
				return Coloring.getGreen(String.valueOf(rs.getInt(1))) + "석";
			} else {
				return Coloring.getRed("매진");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// getAvailableSeatNumOrSoldOutString 함수와 똑같은 로직, 대신 반환만 boolean으로 한다.
	public boolean isAvailableBlock(int block_num) {
		try {
			String loadAvailableSeatNum_sql = String.format(""
				+ "SELECT COUNT(*) AS availableSeat_num "
				+ "FROM seats "
				+ "WHERE game_id = %d AND seat_block = %d AND status = 0;", ReserveService.gameId, block_num);
			ResultSet rs = dao.select(loadAvailableSeatNum_sql);
			rs.next();

			if(rs.getInt(1) != 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * DB에서 가져오는 'block_scope' column이
	 * "111, 112, 113 ..."과 같은 형태로 저장되어있다.
	 * 
	 * 때문에 문자열을 적절한 형태로 가공한 후에 set에 추가하는 과정이 필요하다.
	 * 해당 메소드는 위와 같은 일련의 과정을 수행한 후,
	 * 좌석 타입에 따라 사용자가 입력가능한 block의 수 집합을 반환하는 기능을 수행한다.
	 * 
	 * @return available to reserve seat block set.
	 */
	public HashSet<Integer> getAvailableSeatBlockSet(String seatType) throws InterruptedException {
		// premium석인 경우, 바로 null return.
		if(seatType.equals("premium")) {
			return null;
		}

		// premium석이 아닌 경우의 로직.
		try {
			HashSet<Integer> seatBlockSet = new HashSet<>();
			String loadSeatBlock_sql = String.format(
				"SELECT block_scope FROM seatType WHERE seatType = '%s'", seatType);
			ResultSet rs = dao.select(loadSeatBlock_sql);

			rs.next();

			// 공백제거.
			String noSpace_scope = rs.getString(1).replace(" ", "");

			// 블록 번호 하나씩 배열에 담기.
			String[] blocks = noSpace_scope.split(",");

			// 블록 번호를 int로 바꿔서 set에 담기.
			for(String b : blocks) {
				int b_int = Integer.parseInt(b);
				
				// 예매 가능한 블록이라면 seatBlockSet에 추가, 아니라면 추가하지 않음.
				if(isAvailableBlock(b_int)){
					seatBlockSet.add(b_int);					
				}
			}

			rs.close();
			return seatBlockSet;
		} catch(SQLException e) {
			System.out.println("ReserveDAO.showSeatBlock method throw SQLE!");
		}
		return null;
	}
}
