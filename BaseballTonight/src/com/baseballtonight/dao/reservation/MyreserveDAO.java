package com.baseballtonight.dao.reservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.SQLUtil;

public class MyreserveDAO {
	DAO dao = new DAO();
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

	public static StringBuilder trimDateAndTime(String dat, String dow) {
		StringBuilder DAT = new StringBuilder();
		DAT.append(dat.substring(0, 4) + "년 ");
		DAT.append((dat.substring(5, 6).equals("0") ? dat.substring(6, 7) : dat.substring(5, 7)) + "월 ");
		DAT.append((dat.substring(8, 9).equals("0") ? dat.substring(9, 10) : dat.substring(8, 10)) + "일 ");
		DAT.append(dow + " ");
		DAT.append(dat.substring(11, 16).replace(":", "시 ") + "분 ");
		return DAT;
	}

	public boolean showReservationList(String userID) throws IOException, InterruptedException {
		try {
			String sql = "SELECT reservationID,\n"
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
				+ "WHERE userID =" + "'" + userID + "'";
			ResultSet rs = dao.select(sql);
			System.out.println();
			Coloring.greenOut(userID + " 회원님의 예매 정보입니다.");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
			boolean emptyReservation = true;
			while(rs.next()) {
				int reservationID = rs.getInt(1);
				String gameName = rs.getString(5);
				String stadium = rs.getString(6);
				String DayOfWeek = SQLUtil.getDayOfWeek(rs.getInt(8));
				StringBuilder startWhen = MyreserveDAO.trimDateAndTime(rs.getString(7), DayOfWeek);
				String seatType = rs.getString(2);
				int seatBlock = rs.getInt(3);
				System.out.print("예매번호: " + reservationID + " | ");
				System.out.print(Coloring.cyan + gameName + Coloring.exit);
				System.out.print(" | ");
				System.out.print(Coloring.yellow + stadium + Coloring.exit);
				System.out.print(" | ");
				System.out.print(seatType+"석 " + seatBlock + "블록 ");				
				System.out.print(" | ");
				System.out.print(new SeatDAO().getColumnAndRowBySeatId(rs.getInt("seat_id")));				
				System.out.print(" | ");
				System.out.print(startWhen);
				System.out.print("\n--------------------------------------------------------------------------------------------------------------------------------\n");
				emptyReservation = false;
			}
			rs.close();
			return emptyReservation;
		} catch(SQLException e) {
			System.out.println("showReservationList SQLE!");
			return false;
		}
	}
}