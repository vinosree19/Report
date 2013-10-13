package com.report.util;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.report.beans.Order;

public class OrderDataModel extends ListDataModel<Order> implements
		SelectableDataModel<Order>, Serializable {

	private static final long serialVersionUID = 1L;

	public OrderDataModel() {

	}

	public OrderDataModel(List<Order> orders) {
		super(orders);
	}

	@Override
	public Order getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getWrappedData();

		for (Order order : orders) {
			if (order.getOrdernum().equals(rowKey))
				return order;
		}

		return null;
	}

	@Override
	public Object getRowKey(Order order) {
		return order.getOrdernum();
	}

}
