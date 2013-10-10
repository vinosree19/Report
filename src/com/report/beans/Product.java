package com.report.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "product")
@SessionScoped
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String product;
	private String descripton;
	private double rate;
	public static List<Product> productList;
	private static List<String> list = new ArrayList<String>();
	private static Map<String, Double> map = new HashMap<String, Double>();

	public Product() {

	}

	public Product(String productIn, String descriptonIn, double rateIn) {
		this.product = productIn;
		this.descripton = descriptonIn;
		this.rate = rateIn;
		list.add(productIn);
		map.put(productIn, rateIn);
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public static List<Product> getProductList() {
		return productList;
	}

	public static void setProductList(List<Product> productList) {
		Product.productList = productList;
	}

	public List<String> getList() {
		return list;
	}

	@SuppressWarnings("static-access")
	public void setList(List<String> list) {
		this.list = list;
	}

	public static Map<String, Double> getMap() {
		return map;
	}

	public static void setMap(Map<String, Double> map) {
		Product.map = map;
	}

	public void setProductRate() {
		this.rate = map.get(product);
	}
	
	public void setTotalRate() {
		System.out.println("prod");
	}
}
