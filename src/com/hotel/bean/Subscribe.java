package com.hotel.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_subscribe")
public class Subscribe {

	private String subscribeId;
	private String userId;
	private String hotelId;
	private String houseId;
	private String isUnsubscribe = "0";//是否已退订   (0:未退订的；1:已经退订的)
	private Timestamp subscribeTime;//预订时间
	private Timestamp unsubscribeTime;//退订时间

	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(String subscribeId) {
		this.subscribeId = subscribeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getIsUnsubscribe() {
		return isUnsubscribe;
	}
	public void setIsUnsubscribe(String isUnsubscribe) {
		this.isUnsubscribe = isUnsubscribe;
	}
	public Timestamp getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Timestamp subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public Timestamp getUnsubscribeTime() {
		return unsubscribeTime;
	}
	public void setUnsubscribeTime(Timestamp unsubscribeTime) {
		this.unsubscribeTime = unsubscribeTime;
	}
}
