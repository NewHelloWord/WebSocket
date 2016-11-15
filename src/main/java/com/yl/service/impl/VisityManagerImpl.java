package com.yl.service.impl;

import com.yl.dao.VisityDao;
import com.yl.model.Visit;
import com.yl.service.VisityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by JerryMouse on 2016/11/15.
 */
@Service("VisityManager")
@Transactional
public class VisityManagerImpl implements VisityManager{

    @Autowired
    private VisityDao<Visit> visitVisityDao;

    public void addVisity(Visit visit) {
        visitVisityDao.save(visit);
    }

    public List<Visit> findVisity() {
        List<Visit> list = visitVisityDao.findVisit();
        return list;
    }
}
