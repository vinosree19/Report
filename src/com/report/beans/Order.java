package com.report.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.report.dao.OrderDAO;
import com.report.util.Util;

@ManagedBean(name = "order")
@SessionScoped
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer ordernum;
	private Date date;
	private String prodid;
	private Double quantity;
	private Double total;
	private static String salesman;
	private String desc;

	private Double rate;
	private static List<Product> productList;
	private static List<String> list;
	private static Map<String, Double> map;

	public Order() {
	}

	public Order(Integer ordernumIn, Date dateIn, String prodidIn,
			Double quantityIn, Double totalIn, String salesmanIn) {
		this.ordernum = ordernumIn;
		this.date = dateIn;
		this.prodid = prodidIn;
		this.quantity = quantityIn;
		this.total = totalIn;
		Order.salesman = salesmanIn;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		Order.salesman = salesman;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		Order.productList = productList;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		Order.list = list;
	}

	public Map<String, Double> getMap() {
		return map;
	}

	public void setMap(Map<String, Double> map) {
		Order.map = map;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setProductRate() {
		this.rate = map.get(prodid);
	}

	public void setTotalRate() {
		if (null != rate && null != quantity) {
			this.total = rate * quantity;
		}
	}

	public String placeOrder() {
		if (prodid.equals("--SELECT--")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Please select the product", "Please!"));
		} else {
			boolean result = OrderDAO.order(prodid, quantity, total, salesman);
			if (result) {
				return "login";
			} else {
				setEmpty();
				HttpSession session = Util.getSession();
				OrderDAO.getOrder(session.getAttribute("username").toString());
				return "dashboard";
			}
		}
		return null;
	}

	private void setEmpty() {
		this.prodid = "";
		this.quantity = 1.0;
		this.total = 0.0;
		this.rate = 0.0;
	}

}
