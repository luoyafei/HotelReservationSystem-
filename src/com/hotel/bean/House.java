package com.hotel.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_house")
public class House {
	
	private String houseId;
	private String belongHotelId;//所属酒店
	private String browseMap;//预览图
	private String houseInfo;//房型信息
	private String cheapService;//优惠服务
	private String price;//价格
	private String isOrder;//是否被预定
	private Timestamp createDate;
	
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getBelongHotelId() {
		return belongHotelId;
	}
	public void setBelongHotelId(String belongHotelId) {
		this.belongHotelId = belongHotelId;
	}
	public String getBrowseMap() {
		return browseMap;
	}
	public void setBrowseMap(String browseMap) {
		this.browseMap = browseMap;
	}
	public String getHouseInfo() {
		return houseInfo;
	}
	public void setHouseInfo(String houseInfo) {
		this.houseInfo = houseInfo;
	}
	public String getCheapService() {
		return cheapService;
	}
	public void setCheapService(String cheapService) {
		this.cheapService = cheapService;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
