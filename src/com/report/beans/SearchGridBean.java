package com.report.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "searchGridBean")
@SessionScoped
public class SearchGridBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static List<Order> orderList;
	private static Map<String, Order> ordermap;


	public SearchGridBean() {
		ordermap = new HashMap<String, Order>();
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		SearchGridBean.orderList = orderList;
	}

	public Map<String, Order> getOrdermap() {
		return ordermap;
	}

	public void setOrdermap(Map<String, Order> ordermap) {
		SearchGridBean.ordermap = ordermap;
	}

}
