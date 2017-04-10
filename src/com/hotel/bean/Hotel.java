package com.hotel.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_hotel")
public class Hotel {
	
	private String hotelId;
	private String hotelName;//酒店名称
	private String hotelAddress;//酒店地址
	private String inOUtTimeDesc;//入离时间说明
	private String hotelTel;//酒店电话
	private String internetService;//上网服务
	private String openTime;//开业时间
	private String hotelDevice;//酒店设备
	private String hotelService;//酒店服务
	private String hotelBrief;//酒店简介
	private String browseMap;//浏览图 
	private Timestamp createDate;//创建时间
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getInOUtTimeDesc() {
		return inOUtTimeDesc;
	}
	public void setInOUtTimeDesc(String inOUtTimeDesc) {
		this.inOUtTimeDesc = inOUtTimeDesc;
	}
	public String getHotelTel() {
		return hotelTel;
	}
	public void setHotelTel(String hotelTel) {
		this.hotelTel = hotelTel;
	}
	public String getInternetService() {
		return internetService;
	}
	public void setInternetService(String internetService) {
		this.internetService = internetService;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getHotelDevice() {
		return hotelDevice;
	}
	public void setHotelDevice(String hotelDevice) {
		this.hotelDevice = hotelDevice;
	}
	public String getHotelService() {
		return hotelService;
	}
	public void setHotelService(String hotelService) {
		this.hotelService = hotelService;
	}
	public String getHotelBrief() {
		return hotelBrief;
	}
	public void setHotelBrief(String hotelBrief) {
		this.hotelBrief = hotelBrief;
	}
	public String getBrowseMap() {
		return browseMap;
	}
	public void setBrowseMap(String browseMap) {
		this.browseMap = browseMap;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
