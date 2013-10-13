package com.report.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "lineChartBean")
@SessionScoped
public class LineChartBean {

	private CartesianChartModel model;

	private static List<Order> chartModelList;

	public LineChartBean() {
		model = new CartesianChartModel();
		refreshChart();
	}

	public LineChartBean(List<Order> list) {
		model = new CartesianChartModel();
		refreshChart();
	}

	public CartesianChartModel getModel() {
		return model;
	}

	public List<Order> getChartModelList() {
		return chartModelList;
	}

	public void setChartModelList(List<Order> chartModelList) {
		LineChartBean.chartModelList = chartModelList;
		refreshChart();
	}

	public void refreshChart() {
		model = new CartesianChartModel();
		if (null != chartModelList) {
			for (Order order : chartModelList) {
				ChartSeries chart = new ChartSeries();
				chart.setLabel(order.getPerson());
				chart.set(order.getDate(), order.getTotal());
				model.addSeries(chart);
			}
		}
	}

}
