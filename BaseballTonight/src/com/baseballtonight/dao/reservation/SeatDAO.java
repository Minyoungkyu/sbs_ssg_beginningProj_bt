package com.baseballtonight.dao.reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.service.reservation.ReserveService;
import com.baseballtonight.util.Coloring;

public class SeatDAO {
	DAO dao = new DAO();

	public int getSeatId(int row, int column) {
		String loasSeatId_sql = String.format(
			"SELECT seat_id FROM baseball_tonight.seats \n"
				+ "WHERE \n"
				+ "game_id = %d AND\n"
				+ "seat_block = %d AND \n"
				+ "seat_row = %d AND \n"
				+ "seat_column = %d",
			ReserveService.gameId, ReserveService.seatBlock, row, column);

		ResultSet rs = dao.select(loasSeatId_sql);
		try {

			rs.next();
			int seat_id = rs.getInt("seat_id");
			return seat_id;

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String loadSeatStatusToOX(int row, int column) {
		String loasSeatStatus_sql = String.format(
			"SELECT status FROM baseball_tonight.seats \n"
				+ "WHERE \n"
				+ "game_id = %d AND\n"
				+ "seat_block = %d AND \n"
				+ "seat_row = %d AND \n"
				+ "seat_column = %d",
			ReserveService.gameId, ReserveService.seatBlock, row, column);
		ResultSet rs = dao.select(loasSeatStatus_sql);
		try {
			rs.next();
			int status = rs.getInt("status");

			// 예약 가능한 경우 (0)
			if(status == 0) {

				return Coloring.getCyan("O");

				// 예약 불가능한 경우 (1)
			} else {
				return Coloring.getRed("X");
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isAvailableSeat(int row, int column) {
		String loasSeatStatus_sql = String.format(
			"SELECT status FROM baseball_tonight.seats \n"
				+ "WHERE \n"
				+ "game_id = %d AND\n"
				+ "seat_block = %d AND \n"
				+ "seat_row = %d AND \n"
				+ "seat_column = %d",
			ReserveService.gameId, ReserveService.seatBlock, row, column);
		ResultSet rs = dao.select(loasSeatStatus_sql);
		try {
			rs.next();
			int status = rs.getInt("status");

			// 예약 가능한 경우 (0)
			if(status == 0) {

				return true;

				// 예약 불가능한 경우 (1)
			} else {
				return false;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getNumberOfAvailableSeatBySeatType(int game_id, String seat_type) {
		String getNumberOfAvailableSeat_sql = String.format(
			"SELECT COUNT(*) FROM seats WHERE game_id = %d AND seat_type = '%s' AND status = 0;",
			game_id, seat_type);

		ResultSet rs = dao.select(getNumberOfAvailableSeat_sql);
		try {
			rs.next();
			return rs.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getColumnAndRowBySeatId(int seat_id) {
		try {
			String loasColumnAndRow_sql = String.format("SELECT seat_column, seat_row FROM baseball_tonight.seats\n"
				+ "WHERE seat_id = %d", seat_id);
			ResultSet rs = dao.select(loasColumnAndRow_sql);
			rs.next();

			String column = "";
			switch(rs.getInt("seat_column")) {
			case 1:
				column = "a열 ";
				break;

			case 2:
				column = "b열 ";
				break;

			case 3:
				column = "c열 ";
				break;

			case 4:
				column = "d열 ";
				break;

			case 5:
				column = "e열 ";
				break;

			}

			String rst = column + rs.getInt("seat_row") + "번줄";
			return rst;
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
