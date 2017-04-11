package com.hotel.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.hotel.bean.Subscribe;

@Component("subscribeDao")
public class SubscribeDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveSubscribe(Subscribe s) {
		try {
			hibernateTemplate.save(s);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateSubscribe(Subscribe s) {
		try {
			hibernateTemplate.update(s);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteSubscribe(Subscribe s) {
		try {
			hibernateTemplate.delete(s);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public Subscribe getSubscribeById(String sId) {
		return hibernateTemplate.get(Subscribe.class, sId);
	}
	
	/**
	 * 主体查询为userId， 获取用户的所有创建的订单
	 * @param userId
	 * @param isUnsubscribe (-1:获取所有的订单，不区分；0:获取未退订的；1:获取已经退订的)
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<Subscribe> getSubscribesByUserId(final String userId, final String isUnsubscribe, final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Subscribe>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<Subscribe> doInHibernate(Session session) throws HibernateException, SQLException {
					if(start == -1 && length == -1 && isUnsubscribe.equals("-1"))
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.userId = :userId").setString("userId", userId).list();
					else if(start == -1 && length == -1 && !isUnsubscribe.equals("-1"))
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.userId = :userId and s.isUnsubscribe = :isUnsubscribe").setString("userId", userId).setString("isUnsubscribe", isUnsubscribe).list();
					else if(start != -1 && length != -1 && isUnsubscribe.equals("-1"))
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.userId = :userId").setString("userId", userId).setFirstResult(start).setMaxResults(length).list();
					else
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.userId = :userId and s.isUnsubscribe = :isUnsubscribe").setString("userId", userId).setString("isUnsubscribe", isUnsubscribe).setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通过酒店Id，获取酒店收到的所有订单，
	 * @param hotelId
	 * @param isUnsubscribe (-1:获取所有的订单，不区分；0:获取未退订的；1:获取已经退订的)
	 * @param start
	 * @param length
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<Subscribe> getSubscribesByHotelId(final String hotelId, final String isUnsubscribe, final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Subscribe>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<Subscribe> doInHibernate(Session session) throws HibernateException, SQLException {
					if(start == -1 && length == -1 && isUnsubscribe.equals("-1"))
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.hotelId = :hotelId").setString("hotelId", hotelId).list();
					else if(start == -1 && length == -1 && !isUnsubscribe.equals("-1"))
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.hotelId = :hotelId and s.isUnsubscribe = :isUnsubscribe").setString("hotelId", hotelId).setString("isUnsubscribe", isUnsubscribe).list();
					else if(start != -1 && length != -1 && isUnsubscribe.equals("-1"))
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.hotelId = :hotelId").setString("hotelId", hotelId).setFirstResult(start).setMaxResults(length).list();
					else
						return (List<Subscribe>) session.createQuery("from Subscribe s where s.hotelId = :hotelId and s.isUnsubscribe = :isUnsubscribe").setString("hotelId", hotelId).setString("isUnsubscribe", isUnsubscribe).setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
