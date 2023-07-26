package com.baseballtonight.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	
	Connection con;
	
	public DAO() {
		String url = "jdbc:mysql://localhost:3306/baseball_tonight";
		try {
			con = DriverManager.getConnection(url, "root", "qkqh134679258");
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	public ResultSet select(String SQL) {
		ResultSet rs;
		try {
			Statement state = con.createStatement();
			rs = state.executeQuery(SQL);
			return rs;
		} catch(SQLException e) {
			System.out.println(e);
			System.out.println("DAO에서 예외발생");
		}
		return null;
	}
	
	public Statement getState() {
		try {
			Statement newState = con.createStatement();
			return newState;
		} catch(SQLException e) {
			System.out.println(e);
			System.out.println("DAO에서 예외발생");
		}
		return null;
	}

	public void update(String SQL) {
		try {
			Statement state = con.createStatement();
			state.executeUpdate(SQL);
		} catch(SQLException e) {
			System.out.println(e);
			System.out.println("DAO update method 이거 예외");
		}
	}

}
