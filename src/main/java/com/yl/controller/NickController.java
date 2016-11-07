package com.yl.controller;

import chat.CoreServer;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ChineseName;
import utils.UuidUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JerryMouse on 2016/11/1.
 */
@Controller
public class NickController {

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
        return  jsonObject.toString();
    }

}
