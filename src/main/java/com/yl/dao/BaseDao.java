package com.yl.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Yao on 2016/8/1.
 */
public interface BaseDao<T extends Serializable> {

    public T findById(Serializable id, Class<T> entityClass);

    public List<T> findAll(Class<T> entityClass);

    public T deleteById(Serializable id, Class<T> entityClass);

    public T update(T entity);

    public T saveOrUpdate(T entity);

    public Serializable insert(T entity);

    /**
     * HQL查询
     * @param queryString
     * @param params
     * @return List<T>
     */
    public List<T> complexQuery(String queryString, Object[] params);

    /**
     * HQL查询
     * @param queryString
     * @param params
     * @return List<T>
     */
    public List<T> complexQuery(String queryString, List<Object> params);

    /**
     * SQL查询
     * @param sql
     * @param params
     * @param entityClass
     * @return List<T>
     */
    public List<T> complexQuery(String sql, List<Object> params, Class<T> entityClass);

    /**
     * SQL查询
     * @param sql
     * @param params
     * @param entityClass
     * @return List<T>
     */
    public List<T> complexQuery(String sql, Object[] params, Class<T> entityClass);

    /**
     * SQL查询
     * @param queryString
     * @param params
     * @return List<Object>
     */
    public List<Object> complexQueryFields(String queryString, Object[] params);



    /**
     * SQL查询
     * @param queryString
     * @param List<Object> params
     * @return List<Object>
     */
    public List<Object> complexQueryFields(String queryString, List<Object> params);

    /**
     * SQL查询
     * @param queryString
     * @param List<Object> params
     * @return List<Object>
     */
    public List<Map<String, Object>> complexQueryFields4Map(String queryString, List<Object> params);

    public Integer complexQuery(String queryString, List<Object> params, Integer goodsId);

    public Integer complexQuery(String queryString, Serializable variableId);

    public Integer excuteUpdate(String queryString, Object[] params);

    public HibernateDaoSupport getDaoSupport();

    /**
     * 获取总记录数
     * @param hql
     * @param values
     * @return
     */
    public Integer calcData(String hql, List<Object> values);

    //查询获得的总记录数
    public Integer countByHql(String hql, List<Object> values);

    /**
     * 分页查询
     * @param hql  查询语句
     * @param pageStr 开始的位置
     * @param pageSize 每页数量
     * @param values  参数
     * @return 结果集
     */
    public List<T> findListByHqL(String hql, Integer pageStr, Integer pageSize,
                                 List<Object> values);

    /**
     * sql语句根据条件获取总记录树
     * @param sql
     * @param values
     * @param entityClass
     * @return
     */
    public Integer calcData(String sql, List<Object> values, Class<T> entityClass);

    /**
     * 根据条件查询，获得记录
     * @param hql
     * @param values
     * @param entityClass
     * @return
     */
    public Integer countBySql(String sql, List<Object> values, Class<T> entityClass);

    /**
     * 根据条件查询，获得记录
     * @param sql sql查询语句
     * @param values 参数数组
     * @return
     */
    public Integer countBySql(String sql, Object[] values);

    /**
     * 基于sql分页查询
     * @param hql
     * @param pageStr 每页开始
     * @param pageSize 每页数量
     * @param values 参数
     * @param entityClass 类类型
     * @return 结果集
     */
    public List<T> findListByHqL(String sql, Integer pageStr, Integer pageSize,
                                 List<Object> values, Class<T> entityClass);

    /**
     * 返回list<Object>
     * @param hql
     * @param pageIndex
     * @param pageSize
     * @param params
     * @return List<Object>
     */
    public List<Object> findByHql(String hql, Integer pageIndex, Integer pageSize, List<Object> params);

    public List<Map<String,Object>> findByHql(String hql, List<Object> values, Integer pageIndex, Integer pageSize);

    public List<Map<String,Object>> findBySql(String sql, List<Object> values, Integer pageIndex, Integer pageSize);
    /**add
     * 批量保存
     * *
     */
    public void save(List<T> entities);
    public Integer countBySql(String sql, List<Object> values);

    public Integer calcDataSql(String sql, List<Object> values);

    /**
     * 根据SQL语句查询唯一对象
     * @param sql
     * @param values
     * @return
     */
    public Object findObjectBySql(String sql, List<Object> values);
    /**
     * 保存任何实体
     * @param o
     * @return
     */
    public Object saveObject(Object o);
}
