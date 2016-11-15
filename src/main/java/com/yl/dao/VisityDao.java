package com.yl.dao;

import com.yl.model.Visit;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JerryMouse on 2016/11/15.
 */
public interface VisityDao<T> extends BaseDao<Visit> {

    public Serializable save(T o);

    public List<Visit> findVisit();

}
