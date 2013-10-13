package com.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.report.beans.Dashboard;
import com.report.beans.Order;
import com.report.util.Database;

public class OrderDAO {

	public static boolean order(String prodid, Double quantity, Double total,
			String salesman) {
		Connection connection = null;
		PreparedStatement ps = null;
		Date date = new Date();
		try {
			connection = Database.getConnection();
			String query = "INSERT INTO order_t (order_date, product, quantity, total, salesman) "
					+ "VALUES (?, ?, ?, ?, ?) ";
			ps = connection.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ps.setString(2, prodid);
			ps.setDouble(3, quantity);
			ps.setDouble(4, total);
			ps.setString(5, salesman);
			boolean rs = ps.execute();
			return rs;

		} catch (SQLException ex) {
			System.out.println("Error in Order Product -->" + ex.getMessage());
		} finally {
			Database.close(connection);
		}
		return false;
	}

	public static List<Order> getOrder(String username) {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Order> list = new ArrayList<Order>();
		Dashboard dashboard = new Dashboard();
		try {
			connection = Database.getConnection();
			String query = "SELECT * FROM order_t WHERE salesman= ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("salesman").equalsIgnoreCase(username)) {
					Order order = new Order(rs.getInt("order_num"),
							(Date) rs.getDate("order_date"),
							rs.getString("product"), rs.getDouble("quantity"),
							rs.getDouble("total"), rs.getString("salesman"));
					list.add(order);
				}
			}
			dashboard.setOrderList(list);
		} catch (Exception ex) {
			System.out.println("Error in getOrder() -->" + ex.getMessage());
		} finally {
			Database.close(connection);
		}
		return list;
	}

}
