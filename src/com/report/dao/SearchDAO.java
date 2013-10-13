package com.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.report.beans.BubbleChartBean;
import com.report.beans.LineChartBean;
import com.report.beans.Order;
import com.report.beans.PieChartBean;
import com.report.beans.PieChartBean1;
import com.report.beans.SearchGridBean;
import com.report.beans.SearchSalesReportBean;
import com.report.util.Database;

public class SearchDAO {

	public static void searchReport1(Date frmDate, Date toDate) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SearchGridBean gridBean = new SearchGridBean();
		List<Order> list = new ArrayList<Order>();
		String query = null;
		try {
			connection = Database.getConnection();
			query = "SELECT product_t.prod_id AS prod_id, product_t.prod_desc AS prod_desc, order_t.quantity AS quantity, order_t.total AS total, order_t.salesman AS salesman FROM order_t "
					+ " INNER JOIN product_t ON order_t.product =  product_t.prod_id WHERE order_date>=? AND order_date<=?";
			ps = connection.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(frmDate.getTime()));
			ps.setDate(2, new java.sql.Date(toDate.getTime()));
			rs = ps.executeQuery();
			while (rs.next()) {
				if (gridBean.getOrdermap().containsKey(rs.getString("prod_id"))) {
					AddValue(rs,
							gridBean.getOrdermap().get(rs.getString("prod_id")));
				} else {
					gridBean.getOrdermap().put(rs.getString("prod_id"),
							SearchGrid1(rs));
				}
			}
			Iterator<?> it = gridBean.getOrdermap().entrySet().iterator();
			while (it.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry map = (Map.Entry) it.next();
				list.add((Order) map.getValue());
			}

			gridBean.setOrderList(list);
			createChartValue(list);

		} catch (SQLException ex) {
			System.out.println("Error in searchReport1 -->" + ex.getMessage());
		} finally {
			Database.close(connection);
		}
	}

	public static void searchReport2() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SearchSalesReportBean gridBean = new SearchSalesReportBean();
		List<Order> salesList = new ArrayList<Order>();
		String query = null;
		try {
			connection = Database.getConnection();
			query = "SELECT * FROM order_t";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (gridBean.getSalesReportMap().containsKey(
						rs.getString("salesman"))) {
					AddValue(
							rs,
							gridBean.getSalesReportMap().get(
									rs.getString("salesman")));
				} else {
					gridBean.getSalesReportMap().put(rs.getString("salesman"),
							SearchGrid2(rs));
				}
			}
			Iterator<?> it1 = gridBean.getSalesReportMap().entrySet()
					.iterator();
			while (it1.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry map1 = (Map.Entry) it1.next();
				salesList.add((Order) map1.getValue());
			}
			gridBean.setSalesReportLst(salesList);
			createChartValue1(salesList);
		} catch (SQLException ex) {
			System.out.println("Error in searchReport2 -->" + ex.getMessage());
		} finally {
			Database.close(connection);
		}
	}

	private static Order SearchGrid1(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setProdid(rs.getString("prod_id"));
		order.setDesc(rs.getString("prod_desc"));
		order.setQuantity(rs.getDouble("quantity"));
		order.setTotal(rs.getDouble("total"));
		order.setPerson(rs.getString("salesman"));
		return order;
	}

	private static Order SearchGrid2(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setDate(rs.getDate("order_date"));
		order.setQuantity(rs.getDouble("quantity"));
		order.setTotal(rs.getDouble("total"));
		order.setPerson(rs.getString("salesman"));
		return order;
	}

	private static Order AddValue(ResultSet rs, Order order)
			throws SQLException {
		order.setQuantity(order.getQuantity() + rs.getDouble("quantity"));
		order.setTotal(order.getTotal() + rs.getDouble("total"));
		return order;
	}

	public static void createChartValue(List<Order> list) {
		PieChartBean pieChartBean = new PieChartBean(list);
		pieChartBean.setPieModelList(list);
		BubbleChartBean bubbleChartBean = new BubbleChartBean(list);
		bubbleChartBean.setBubbleModelList(list);
	}

	public static void createChartValue1(List<Order> list) {
		PieChartBean1 pieChartBean = new PieChartBean1(list);
		pieChartBean.setPieModelList(list);
		LineChartBean lineChartBean = new LineChartBean(list);
		lineChartBean.setChartModelList(list);
	}

}
