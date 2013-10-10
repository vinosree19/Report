package com.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.report.beans.Order;
import com.report.util.Database;

public class OrderDAO {

	public static void order() {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = Database.getConnection();
			String query = "INSERT INTO order_t (username, password, active) "
								+ "VALUES (?, ?, ?) ";
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, pwd);
			ps.setString(3, "Y");
			boolean rs = ps.execute();
			
			
		} catch (SQLException ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(connection);
		}
	}

}
