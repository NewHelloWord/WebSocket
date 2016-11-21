package com.yl.controller;

import chat.CoreServer;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import com.yl.model.Visit;
import com.yl.service.VisityManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.ChineseName;
import utils.HttpUtils;
import utils.IpUtils;
import utils.UuidUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JerryMouse on 2016/11/1.
 */
@Controller
public class NickController {

    @Autowired
    private VisityManager visityManager;

    //获取昵称和唯一标识
    @RequestMapping(value = "/getNick.htm")
    @ResponseBody
    public String getNick(HttpServletRequest request){
        String name = ChineseName.createName();
        String uid = UuidUtils.getUuid();
        ConcurrentHashMap nickMap = CoreServer.getNickMap();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nick",name);
        jsonObject.put("uid",uid);
        jsonObject.put("nickMap",nickMap);

        String s = IpUtils.getIpAddr(request);
        if(s.equals("127.0.0.1") || s.split("\\.").length != 4){
            s = "183.128.233.233";
        }
        try {
            String adress = IpUtils.getAddress(s);
//            System.out.println("adress===="+adress);
//            //中国=华东=浙江省=杭州市==电信

            boolean reLogin = Util.isEmptyString(request.getParameter("reLogin"));
            if(reLogin){
                //TODO  在这里进行保存数据库操作
                Visit visit = new Visit();
                visit.setNick(name);
                visit.setAddress(adress);
                visit.setJoinTime(new Date());
                visit.setUid(uid);
                if(s.equals("183.128.233.233")){
                    visit.setIp("127.0.0.1");
                }else {
                    visit.setIp(s);
                }
                visityManager.addVisity(visit);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return  jsonObject.toString();
    }

    @RequestMapping(value = "/getVisitList.htm")
    @ResponseBody
    public ModelAndView getVisitList(HttpServletRequest request){
        List<Visit> list = visityManager.findVisity();
        return new ModelAndView("/userHistory").addObject("list",list);
    }

}
