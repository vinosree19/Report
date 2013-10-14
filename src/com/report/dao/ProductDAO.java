package com.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.report.beans.Order;
import com.report.beans.Product;
import com.report.util.Database;

public class ProductDAO {

	public static void initProduct(String username) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		List<String> strings = new ArrayList<String>();
		Map<String, Double> map = new HashMap<String, Double>();
		Order order = new Order();
		try {
			connection = Database.getConnection();
			String query = "SELECT * FROM product_t";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			Product product = new Product("--SELECT--", "Select One", 0.0);
			strings.add(0, "--SELECT--");
			map.put("--SELECT--", 0.0);
			list.add(product);
			while (rs.next()) {
				Product product2 = new Product(rs.getString("prod_id"),
						rs.getString("prod_desc"), rs.getDouble("rate"));
				list.add(product2);
				strings.add(rs.getString("prod_id"));
				map.put(rs.getString("prod_id"), rs.getDouble("rate"));
			}
			order.setProductList(list);
			order.setMap(map);
			order.setList(strings);
			order.setSalesman(username);
		} catch (SQLException ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(connection);
		}
	}

}
