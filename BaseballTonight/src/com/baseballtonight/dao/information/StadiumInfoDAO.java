package com.baseballtonight.dao.information;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.util.Coloring;

public class StadiumInfoDAO {
    private String setNull = Coloring.getPurple("등록된 정보가 없습니다\n");
    ResultSet resultSet;
    public StadiumInfoDAO(int parkId) {
    	DAO dao = new DAO();
    	try {
    		String sql = String.format("select * from park_info where id = %d", parkId);
    		resultSet = dao.select(sql);
    		resultSet.next();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    public String getParkName() {
        String name = "";
        try {
            name = resultSet.getString("name");
        } catch (SQLException e) {}

        return name;
    }

    public String getParkAddress() {
        String address = "";
        try {
            address = resultSet.getString("address");
        } catch (SQLException e) {}
        return address;
    }

    public String getParkFood() {
        String food = "";
        try {
            food = resultSet.getString("food");
            food = food.replace(",", Coloring.getCyan(""));
        } catch (SQLException e) {}
        return food;
    }

    public String getParkBus() {
        String bus = "";
        try {
            bus = resultSet.getString("traffic_bus");
        } catch (SQLException e) {}

        return bus;

    }

    public String getParkBusstop() {
        String busstop = "";

        try {
            busstop = resultSet.getString("traffic_busstop");
        } catch (SQLException e) {}

        return busstop;

    }

    public String getParkSub() {

        String sub = "";
        try {
            sub = resultSet.getString("traffic_sub");
        } catch (SQLException e) {}

        if(sub == null) {
            return setNull;
        }
        return sub;
    }

    public String getParkParking() {

        String parking = "";
        try {
            parking = resultSet.getString("traffic_parking");
        } catch (SQLException e) {}

        if(parking == null) {
            return setNull;
        }
        return parking;
    }



}
