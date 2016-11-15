package com.yl.dao.impl;

import com.yl.dao.BaseDao;
import com.yl.dao.VisityDao;
import com.yl.model.Visit;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JerryMouse on 2016/11/15.
 */
@Repository("VisityDao")
public class VisityDaoImpl<T> extends BaseDaoImpl<Visit> implements VisityDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public Serializable save(T o) {
        return sessionFactory.getCurrentSession().save(o);
    }

    public List<Visit> findVisit() {
        String hql = "from Visit ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}
