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

import com.hotel.bean.User;

@Component("userDao")
public class UserDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	public boolean saveUser(User user) {
		try {
			hibernateTemplate.save(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean udpateUser(User user) {
		try {
			hibernateTemplate.save(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateUser(User user) {
		try {
			hibernateTemplate.save(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	} 
	public User getUserById(String userId) {
		return hibernateTemplate.get(User.class, userId);
	}
	/**
	 * 获取多个用户
	 * @param start
	 * @param length
	 * @return
	 */
	public List<User> getUsers(final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<User>>() {
				@SuppressWarnings({ "unchecked"})
				@Override
				public List<User> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from User";
					Query q = session.createQuery(hql);
					if(start == -1 && length == -1)
						return (List<User>) q.list();
					else
						return (List<User>) q.setFirstResult(start).setMaxResults(length).list();
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User getUserInUserName(final String userTel) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<User>() {
				@Override
				public User doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from User u where u.userTel = :userTel";
					Query q = session.createQuery(hql);
					q.setString("userTel", userTel);
					if(q.list() != null) {
						return (User) q.list().get(0);
					} else
						return null;
					
				}	
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
