package com.yl.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.HighIPUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JerryMouse on 2016/11/20.
 */
@Controller
public class HighIPController {

    /**
     * 通过Ajax返回地址
     * @param request
     * @return
     */
    @RequestMapping(value = "getAddress.htm",method = RequestMethod.POST)
    @ResponseBody
    public String getAddress(HttpServletRequest request){
        String IP = request.getParameter("IP");
//        String result = HighIPUtils.getStrHighAddress(IP);
        Map<String,Object> map = HighIPUtils.getMapHighAdress(IP);
        String result = JSONObject.fromObject(map).toString();
        return result;
    }


    @RequestMapping(value = "")
    @ResponseBody
    public ModelAndView map(HttpServletRequest request){

        return new ModelAndView("map");
    }

}
