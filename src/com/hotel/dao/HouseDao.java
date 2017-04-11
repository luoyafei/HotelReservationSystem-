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

import com.hotel.bean.House;

@Component("houseDao")
public class HouseDao {
	
	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveHouse(House h) {
		try {
			hibernateTemplate.save(h);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateHouse(House h) {
		try {
			hibernateTemplate.update(h);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteHouse(House h) {
		try {
			hibernateTemplate.delete(h);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	
	/**
	 * 通过房间Id获取房间信息
	 * @param houseId
	 * @return
	 */
	public House getHouseById(final String houseId) {
		return hibernateTemplate.get(House.class, houseId);
	}
	/**
	 * 通过酒店，获取酒店下的所有房间
	 * @param hotelId
	 * @param start
	 * @param length
	 * @return
	 */
	public List<House> getHousesByBelongHotelId(final String hotelId, final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<House>>() {
				@SuppressWarnings({ "unchecked"})
				@Override
				public List<House> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from House h where h.belongHotelId = :belongHotelId";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<House>) q.setString("belongHotelId", hotelId).list();
					else
						return (List<House>) q.setString("belongHotelId", hotelId).setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取所有的房间
	 * @param start
	 * @param length
	 * @return
	 */
	public List<House> getHouses(final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<House>>() {
				@SuppressWarnings({ "unchecked"})
				@Override
				public List<House> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from House";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<House>) q.list();
					else
						return (List<House>) q.setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
