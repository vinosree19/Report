package com.report.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "searchSalesReportBean")
@SessionScoped
public class SearchSalesReportBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static List<Order> salesReportLst;
	private static Map<String, Order> salesReportMap;

	public SearchSalesReportBean() {
		salesReportMap = new HashMap<String, Order>();
	}

	public List<Order> getSalesReportLst() {
		return salesReportLst;
	}

	public void setSalesReportLst(List<Order> salesReportLst) {
		SearchSalesReportBean.salesReportLst = salesReportLst;
	}

	public Map<String, Order> getSalesReportMap() {
		return salesReportMap;
	}

	public void setSalesReportMap(Map<String, Order> salesReportMap) {
		SearchSalesReportBean.salesReportMap = salesReportMap;
	}

}
