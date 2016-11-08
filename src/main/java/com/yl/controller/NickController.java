package com.yl.controller;

import chat.CoreServer;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ChineseName;
import utils.HttpUtils;
import utils.IpUtils;
import utils.UuidUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
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

        String s = IpUtils.getIpAddr(request);
        if(s.equals("127.0.0.1") || s.split(".").length != 4){
            s = "183.128.233.233";
        }
        System.out.println("ip======================"+s);
        try {
            String adress = IpUtils.getAddress(s);
            System.out.println("adress===="+adress);
            //中国=华东=浙江省=杭州市==电信

            //TODO  在这里进行保存数据库操作


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        return  jsonObject.toString();
    }

}
