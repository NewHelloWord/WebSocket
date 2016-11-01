package com.yl.dao.impl;

import com.yl.dao.BaseDao;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yao on 2016/8/1.
 */
@Repository
public class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    private HibernateDaoSupport daoSupport;

    public BaseDaoImpl() {

    }

    public T findById(Serializable id, Class<T> entityClass) {
        return entityClass.cast(getCurrentSession().get(entityClass, id));
    }

    public List<T> findAll(Class<T> entityClass) {

        String entityName = entityClass.getName();

        String queryString = "SELECT e FROM " + entityName + " e";

        return complexQuery(queryString, new Object[]{});
    }

    public T deleteById(Serializable id, Class<T> entityClass) {

        T entity = findById(id, entityClass);

        if (entity != null) {
            getCurrentSession().delete(entity);
            return entity;
        }

        return null;
    }

    public T update(T entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    public T saveOrUpdate(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public Serializable insert(T entity) {
        return getCurrentSession().save(entity);
    }

    public List<T> complexQuery(String queryString, Object[] params) {

        Query query = getCurrentSession().createQuery(queryString);
        for (int index = 0; index < params.length; index++) {
            query.setParameter(index, params[index]);
        }

        @SuppressWarnings("unchecked")
        List<T> entities = query.list();

        if (entities == null)
            return new ArrayList<T>();

        return entities;
    }

    public List<T> complexQuery(String queryString, List<Object> params) {

        Query query = getCurrentSession().createQuery(queryString);
        for (int index = 0; index < params.size(); index++) {
            query.setParameter(index, params.get(index));
        }

        @SuppressWarnings("unchecked")
        List<T> entities = query.list();

        if (entities == null)
            return new ArrayList<T>();

        return entities;
    }

    public List<T> complexQuery(String queryString, List<Object> params, Class<T> entityClass) {

        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
        for (int index = 0; index < params.size(); index++) {
            query.setParameter(index, params.get(index));
        }


        @SuppressWarnings("unchecked")
        List<T> entities = query.addEntity(entityClass).list();

        if (entities == null)
            return new ArrayList<T>();

        return entities;
    }

    public List<T> complexQuery(String sql, Object[] params, Class<T> entityClass) {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        for (int index = 0; index < params.length; index++) {
            query.setParameter(index, params[index]);
        }

        @SuppressWarnings("unchecked")
        List<T> entities = query.addEntity(entityClass).list();

        if (entities == null)
            return new ArrayList<T>();

        return entities;
    }

    public List<Object> complexQueryFields(String queryString, Object[] params) {
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);

        for (int index = 0; index < params.length; index++) {
            query.setParameter(index, params[index]);
        }

        @SuppressWarnings("unchecked")
        List<Object> entities = query.list();

        if (entities == null)
            return new ArrayList<Object>();

        return entities;
    }


    public List<Object> complexQueryFields(String queryString, List<Object> params) {
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);

        for (int index = 0; index < params.size(); index++) {
            query.setParameter(index, params.get(index));
        }

        @SuppressWarnings("unchecked")
        List<Object> entities = query.list();

        if (entities == null)
            return new ArrayList<Object>();

        return entities;
    }

    public List<Map<String, Object>> complexQueryFields4Map(String queryString, List<Object> params) {
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int index = 0; index < params.size(); index++) {
            query.setParameter(index, params.get(index));
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> entities = query.list();

        if (entities == null)
            return null;

        return entities;
    }

    public Integer complexQuery(String queryString, List<Object> params,
                                Integer goodsId) {
        Integer result = 0;
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
        try {
            for (int index = 0; index < params.size(); index++) {
                query.setParameter(0, params.get(index));
                query.setParameter(1, goodsId);
                query.executeUpdate();
                result++;
            }
        } catch (HibernateException e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }

    public Integer complexQuery(String queryString, Serializable variableId) {
        Integer result = 0;
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
        query.setParameter(0, variableId);
        result = query.executeUpdate();
        return result;
    }

    public Integer excuteUpdate(String queryString, Object[] params) {
        Integer result = 0;
        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
        if (params.length > 0) {
            for (int index = 0; index < params.length; index++) {
                query.setParameter(index, params[index]);
            }
        }
        result = query.executeUpdate();
        return result;
    }


    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public HibernateDaoSupport getDaoSupport() {
        return daoSupport;
    }

    // 获取总记录数
    public Integer calcData(String hql, List<Object> values) {
        Integer count = 0;
        try {
            count = countByHql("select count(*) " + hql, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // 获取总记录数
    public Integer calcDataSql(String sql, List<Object> values) {
        Integer count = 0;
        try {
            count = countBySql(sql.replace("select *", "select COUNT(*)"), values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //查询获得的总记录数
    public Integer countByHql(String hql, List<Object> values) {
        Query query = getCurrentSession().createQuery(hql);
        if (null != values && values.size() > 0)
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
            }
        Object obj = query.uniqueResult();
        if (obj instanceof Long)
            return Integer.parseInt(((Long) obj).toString());
        else
            return (Integer) obj;
    }

    //查询获得的总记录数
    public Integer countBySql(String sql, List<Object> values) {
        Query query = getCurrentSession().createSQLQuery(sql);
        if (null != values && values.size() > 0)
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
            }
        Object obj = query.uniqueResult();
        return Integer.parseInt(obj.toString());
    }

    @SuppressWarnings("unchecked")
    public List<T> findListByHqL(String hql, Integer pageStr, Integer pageSize,
                                 List<Object> values) {

        Query query = getCurrentSession().createQuery(hql);
        if (null != values && values.size() > 0)
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
            }
        query.setFirstResult(pageStr);
        query.setMaxResults(pageSize);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findListByHqL(String sql, Integer pageStr, Integer pageSize,
                                 List<Object> values, Class<T> entityClass) {

        Query query = getCurrentSession().createSQLQuery(sql).addEntity(entityClass);
        if (null != values && values.size() > 0)
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
            }
        query.setFirstResult(pageStr);
        query.setMaxResults(pageSize);
        return query.list();
    }

    // 获取总记录数
    public Integer calcData(String sql, List<Object> values, Class<T> entityClass) {
        Integer count = 0;
        try {
            count = countBySql("select count(*) FROM (" + sql + ") a", values, entityClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //查询获得的总记录数
    public Integer countBySql(String sql, List<Object> values, Class<T> entityClass) {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        if (null != values && values.size() > 0)
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
//				System.out.println(values.get(i).getClass()+"/"+values.get(i));
            }
        Object obj = query.uniqueResult();
        return Integer.parseInt(obj.toString());

    }

    //查询获得的总记录数
    public Integer countBySql(String sql, Object[] values) {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        if (null != values && values.length > 0)
            for (int i = 0, len = values.length; i < len; i++) {
                query.setParameter(i, values[i]);
            }
        Object obj = query.uniqueResult();
        return Integer.parseInt(obj.toString());
    }

    // 返回list<Object>
    @SuppressWarnings("unchecked")
    public List<Object> findByHql(String hql, Integer pageIndex, Integer pageSize, List<Object> params) {
        Query query = getCurrentSession().createQuery(hql);
        if (null != params && params.size() > 0)
            for (int i = 0, len = params.size(); i < len; i++) {
                query.setParameter(i, params.get(i));
            }
        if (pageIndex != null && pageSize != null && pageSize > 0) {
            query.setFirstResult(pageIndex);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findByHql(String hql, List<Object> values, Integer pageIndex, Integer pageSize) {
        Query query = getCurrentSession().createQuery(hql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != values && values.size() > 0) {
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
            }
        }
        if (pageIndex != null && pageSize != null) {
            query.setFirstResult(pageIndex);
            query.setMaxResults(pageSize);
        }
        List<Map<String, Object>> list = query.list();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findBySql(String sql, List<Object> values, Integer pageIndex, Integer pageSize) {
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != values && values.size() > 0) {
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
            }
        }
        if (pageIndex != null && pageSize != null) {
            query.setFirstResult(pageIndex);
            query.setMaxResults(pageSize);
        }
        List<Map<String, Object>> list = query.list();
        return list;
    }

    /**
     * add
     * 批量保存
     * *
     */
    public void save(List<T> entities) {
        Session session = getCurrentSession();
        for (T entity : entities) {
            session.save(entity);
        }
    }

    public Object findObjectBySql(String sql, List<Object> values) {
        Query query = getCurrentSession().createSQLQuery(sql);
        if (null != values && values.size() > 0)
            for (int i = 0, len = values.size(); i < len; i++) {
                query.setParameter(i, values.get(i));
            }
        return query.uniqueResult();
    }

    public Object saveObject(Object o) {
        return this.getCurrentSession().save(o);
    }
}
