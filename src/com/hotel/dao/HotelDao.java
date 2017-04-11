package com.hotel.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.hotel.bean.Hotel;

@Component("hotelDao")
public class HotelDao {
	
	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveHotel(Hotel hotel) {
		try {
			hibernateTemplate.save(hotel);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteHotel(Hotel hotel) {
		try {
			hibernateTemplate.delete(hotel);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateHotel(Hotel hotel) {
		try {
			hibernateTemplate.update(hotel);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public Hotel getHotelById(String hotelId) {
		return hibernateTemplate.get(Hotel.class, hotelId);
	}
	public List<Hotel> getHotelsByAddress(final String address, final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Hotel>>() {
				@SuppressWarnings({ "unchecked"})
				@Override
				public List<Hotel> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Hotel h where h.hotelAddress = :hotelAddress";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<Hotel>) q.setString("hotelAddress", address).list();
					else
						return (List<Hotel>) q.setString("hotelAddress", address).setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Hotel> getHotels(final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<Hotel>>() {
				@SuppressWarnings({ "unchecked"})
				@Override
				public List<Hotel> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Hotel";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<Hotel>) q.list();
					else
						return (List<Hotel>) q.setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int getHotelCount() {
		Long count = new Long(0);
		
		try {
			count = hibernateTemplate.execute(new HibernateCallback<Long>() {
				@Override
				public Long doInHibernate(Session session) throws HibernateException, SQLException {
    				String ejbql = "select count(*) from Hotel";
    				return (Long) session.createQuery(ejbql).uniqueResult();
				}
			});
			
		} catch(DataAccessException e) {
			count = 0L;
		}
		
		return count.intValue();
	}
}
