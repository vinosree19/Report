package com.report.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "barChartBean")
@SessionScoped
public class BarChartBean {

	private CartesianChartModel model;

	private static List<Order> chartModelList;

	public BarChartBean() {
		model = new CartesianChartModel();
	}

	public BarChartBean(List<Order> list) {
		model = new CartesianChartModel();
		for (Order order : list) {
			ChartSeries chart = new ChartSeries();
			chart.setLabel(order.getProdid());
			chart.set(order.getSalesman(), order.getTotal());
			model.addSeries(chart);
		}
	}

	public CartesianChartModel getModel() {
		return model;
	}

	public List<Order> getChartModelList() {
		return chartModelList;
	}

	public void setChartModelList(List<Order> chartModelList) {
		BarChartBean.chartModelList = chartModelList;
	}

	public void refreshChart() {
		model = new CartesianChartModel();
		if (null != chartModelList) {
			for (Order order : chartModelList) {
				ChartSeries chart = new ChartSeries();
				chart.setLabel(order.getProdid());
				chart.set(order.getSalesman(), order.getTotal());
				model.addSeries(chart);
			}
		}
	}

}
