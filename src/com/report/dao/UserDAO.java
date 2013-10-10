package com.report.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.report.util.Database;

public class UserDAO {

	public static String login(String username, String password) throws NoSuchAlgorithmException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = Database.getConnection();
			String pwd = Database.encodePassword(password);
			ps = connection.prepareStatement("SELECT username, password "
					+ "FROM user_t WHERE username= ? AND password= ? ");
			ps.setString(1, username);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("username").equalsIgnoreCase(username)
						 && rs.getString("password").equalsIgnoreCase(pwd))
					return null;
				else 
					return "Please Enter the Valid Username and Password";
			} else {
				return "Please Enter the Valid Username and Password";
			}
		} catch (SQLException ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
			return "Error in login()";
		} finally {
			Database.close(connection);
		}
	}

	public static String register(String username, String password) throws NoSuchAlgorithmException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = Database.getConnection();
			if (checkUser(connection, ps, username)) {
				String pwd = Database.encodePassword(password);
				ps = connection
						.prepareStatement("INSERT INTO user_t (username, password, active) "
								+ "VALUES (?, ?, ?) ");
				ps.setString(1, username);
				ps.setString(2, pwd);
				ps.setString(3, "Y");
				boolean rs = ps.execute();
				if (!rs) {
					return "SUCCESS";
				} else {
					return "FAIL";
				}
			} else {
				return "USER";
			}
		} catch (SQLException ex) {
			System.out.println("Error in User Registration -->"
					+ ex.getMessage());
			return "FAIL";
		} finally {
			Database.close(connection);
		}
	}

	private static boolean checkUser(Connection connection,
			PreparedStatement ps, String username) {
		try {
			ps = connection.prepareStatement("SELECT username, password "
					+ "FROM user_t WHERE username= ? ");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("username").equalsIgnoreCase(username))
					return false;
			}
		} catch (SQLException ex) {
			System.out.println("Error in Check User Exist -->"
					+ ex.getMessage());
			return false;
		}
		return true;
	}

}
