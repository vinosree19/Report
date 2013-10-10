package com.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.report.beans.Product;
import com.report.util.Database;

public class ProductDAO {

	public static void initProduct() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		Product prod = new Product();
		prod.setList(new ArrayList<String>());
		try {
			connection = Database.getConnection();
			String query = "SELECT * FROM product_t";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			Product product = new Product("--SELECT--", "Select One", 0.0);
			list.add(product);
			while (rs.next()) {
				Product product2 = new Product(rs.getString("prod_id"),
						rs.getString("prod_desc"), rs.getDouble("rate"));
				list.add(product2);
			}
			Product.setProductList(list);
		} catch (SQLException ex) {
			System.out.println("Error in login() -->" + ex.getMessage());
		} finally {
			Database.close(connection);
		}
	}

}
