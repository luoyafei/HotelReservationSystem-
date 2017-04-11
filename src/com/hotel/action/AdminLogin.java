package com.hotel.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.hotel.bean.Admin;
import com.hotel.dao.AdminDao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("adminLogin")
@Scope("prototype")
public class AdminLogin extends ActionSupport {


	private String adminNum;
	private String password;
	private AdminDao ad;
	
	
	public String getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdminDao getAd() {
		return ad;
	}
	@Resource(name="adminDao")
	public void setAd(AdminDao ad) {
		this.ad = ad;
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
		if(adminNum != null && adminNum.trim().hashCode() != 0 && password != null && password.trim().hashCode() != 0) {
			Admin admin = ad.getAdminByNum(adminNum);
			if(admin != null) {
				if(admin.getAdminPwd().equals(password)) {
					success = true;
					ServletActionContext.getRequest().getSession().setAttribute("admin", admin);
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
}
