package com.report.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "dashboard")
@SessionScoped
public class Dashboard implements Serializable {

	private static final long serialVersionUID = 1L;
	private static List<Order> orderList;
	private Order selectedOrder;

	public Dashboard() {
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		Dashboard.orderList = orderList;
	}

	public Order getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Order selectedOrder) {
		this.selectedOrder = selectedOrder;
	}
}
