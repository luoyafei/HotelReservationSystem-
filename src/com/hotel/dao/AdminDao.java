package com.hotel.dao;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.hotel.bean.Admin;


@Component("adminDao")
public class AdminDao {
	
	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public Admin getAdminByNum(final String adminNum) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<Admin>() {
				@Override
				public Admin doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Admin a where a.adminNum = :adminNum";
					Query q = session.createQuery(hql);
					q.setString("adminNum", adminNum);
					return (Admin) q.list().get(0);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean updateAdmin(Admin admin) {
		try {
			hibernateTemplate.update(admin);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
}
