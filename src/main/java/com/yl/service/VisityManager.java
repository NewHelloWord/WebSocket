package com.yl.service;

import com.yl.model.Visit;

import java.util.List;

/**
 * Created by JerryMouse on 2016/11/15.
 */
public interface VisityManager {

    public void addVisity(Visit visit);

    public List<Visit> findVisity();
}
