package com.yl.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ChineseName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by JerryMouse on 2016/11/1.
 */
@Controller
public class NickController {

    @RequestMapping(value = "/getNick.htm")
    @ResponseBody
    public String getNick(HttpServletRequest request){
        System.out.println("========"+request.getParameter("word"));
        String name = ChineseName.createName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nick",name);
        return  jsonObject.toString();
    }

}
