package com.report.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "pieChartBean")
@SessionScoped
public class PieChartBean {
	private PieChartModel model;

	private static List<Order> pieModelList;

	public PieChartBean() {
		model = new PieChartModel();
	}

	public PieChartBean(List<Order> list) {
		model = new PieChartModel();
		for (Order order : list) {
			model.set(order.getProdid(), order.getTotal());
		}
	}

	public PieChartModel getModel() {
		return model;
	}

	public List<Order> getPieModelList() {
		return pieModelList;
	}

	public void setPieModelList(List<Order> pieModelList) {
		PieChartBean.pieModelList = pieModelList;
	}

	public void refreshChart() {
		model = new PieChartModel();
		if (null != pieModelList) {
			for (Order order : pieModelList) {
				model.set(order.getProdid(), order.getTotal());
			}
		}
	}
}
