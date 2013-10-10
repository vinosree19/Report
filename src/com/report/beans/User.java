package com.report.beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.report.dao.ProductDAO;
import com.report.dao.UserDAO;
import com.report.util.Util;

@ManagedBean(name = "user")
@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userid;
	private String username;
	private String password;
	private String message;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
		public String loginProject() throws NoSuchAlgorithmException {
		String result = UserDAO.login(username, password);
		if (result != null && result != "") {
			setEmpty();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Login! Please Try Again!",
							"Please Try Again!"));
			return "login";
		} else {
			HttpSession session = Util.getSession();
			session.setAttribute("username", username);
			ProductDAO.initProduct();
			return "dashboard";
		}
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		setEmpty();
		return "login";
	}

	public String register() {
		setEmpty();
		return "user";
	}

	public String lostPassword() {
		return null;
	}



	public String submitUser() throws NoSuchAlgorithmException {
		String result = UserDAO.register(username, password);
		setEmpty();
		if (result.equalsIgnoreCase("SUCCESS")) {
			return "login";
		} else if (result.equalsIgnoreCase("USER")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"User Already Exist!", "Please Try Again!"));
			return "user";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Login!", "Please Try Again!"));
			return "user";
		}
	}

	private void setEmpty() {
		username = "";
		password = "";
	}
}
