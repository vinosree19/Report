package com.report.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.report.dao.SearchDAO;

@ManagedBean(name = "report1")
@SessionScoped
public class Report1 implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date frmDate;
	private Date toDate;

	public Report1() {
		frmDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);
		toDate = c.getTime();
	}

	public Date getFrmDate() {
		return frmDate;
	}

	public void setFrmDate(Date frmDate) {
		this.frmDate = frmDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void generate() {
		if (frmDate.compareTo(toDate) >= 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"To date should be after From date",
							"Please Try Again!"));
		} else {
			SearchDAO.searchReport1(frmDate, toDate);
		}
	}

	public String loadChart() {
		return "chart";
	}

	public String loadReport() {
		return "report";
	}

	public String loadSalesmanReport() {
		SearchDAO.searchReport2();
		return "salesmanReport";
	}

}
