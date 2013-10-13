package com.report.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "product")
@SessionScoped
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String product;
	private String descripton;
	private double rate;

	public Product(String productIn, String descriptonIn, double rateIn) {
		this.product = productIn;
		this.descripton = descriptonIn;
		this.rate = rateIn;
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
}
