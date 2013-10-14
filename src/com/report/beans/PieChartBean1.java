package com.report.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name = "pieChartBean1")
@SessionScoped
public class PieChartBean1 {

	private PieChartModel model;

	private static List<Order> pieModelList;

	public PieChartBean1() {
		model = new PieChartModel();
		refreshChart();
	}

	public PieChartBean1(List<Order> list) {
		refreshChart();
	}

	public PieChartModel getModel() {
		return model;
	}

	public List<Order> getPieModelList() {
		return pieModelList;
	}

	public void setPieModelList(List<Order> pieModelList) {
		PieChartBean1.pieModelList = pieModelList;
		refreshChart();
	}

	public void refreshChart() {
		model = new PieChartModel();
		if (null != pieModelList) {
			for (Order order : pieModelList) {
				model.set(order.getPerson(), order.getTotal());
			}
		}
	}

}
