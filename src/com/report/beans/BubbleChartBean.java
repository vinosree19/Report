package com.report.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

@ManagedBean(name = "bubbleChartBean")
@SessionScoped
public class BubbleChartBean {
	private BubbleChartModel model;

	private static List<Order> bubbleModelList;

	public BubbleChartBean() {
		model = new BubbleChartModel();
		refreshChart();
	}

	public BubbleChartBean(List<Order> list) {
		refreshChart();
	}

	public BubbleChartModel getModel() {
		return model;
	}

	public void setModel(BubbleChartModel model) {
		this.model = model;
	}

	public List<Order> getBubbleModelList() {
		return bubbleModelList;
	}

	public void setBubbleModelList(List<Order> bubbleModelList) {
		BubbleChartBean.bubbleModelList = bubbleModelList;
		refreshChart();
	}

	public void refreshChart() {
		model = new BubbleChartModel();
		int i = 5;
		if (null != bubbleModelList) {
			for (Order order : bubbleModelList) {
				i = i + 3;
				BubbleChartSeries series = new BubbleChartSeries();
				series.setLabel(order.getProdid());
				series.setX(order.getTotal().intValue());
				series.setY(order.getQuantity().intValue());
				series.setRadius(i);
				model.add(series);
			}
		}
	}

}
