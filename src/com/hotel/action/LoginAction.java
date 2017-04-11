package com.hotel.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.hotel.bean.User;
import com.hotel.dao.UserDao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {

	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private String userTel;
	private String userPassword;
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public void login() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		if(userTel != null && userTel.trim().hashCode() != 0 && userPassword != null && userPassword.trim().hashCode() != 0) {
			
			User user = userDao.getUserInUserName(userTel);
			
			if(user != null) {
				if(user.getUserPassword().equals(userPassword)) {
					success = true;
					ServletActionContext.getRequest().getSession().setAttribute("user", user);
				} else
					reason = "密码错误";
			} else
				reason = "该账号不存在";
		} else
			reason = "请按要求填写数据";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
	}
	
	public void regist() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		if(userTel != null && userTel.trim().hashCode() != 0 && userPassword != null && userPassword.trim().hashCode() != 0) {
			
			User user = new User();
			user.setUserTel(userTel);
			user.setUserPassword(userPassword);
			if(userDao.saveUser(user)) {
				success = true;
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
			} else
				reason = "存储失败";
		} else
			reason = "请按要求填写数据";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		
		out.flush();
		out.close();	
	}
}
