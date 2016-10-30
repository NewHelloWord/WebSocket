package chat;

import net.sf.json.JSONObject;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by JerryMouse on 2016/10/30.
 *
 * 消息主要处理类
 */
public class CoreServer {

    private static int onlineNum = 0;  //当前在线数

    private static ConcurrentHashMap<String, String> nickMap = new ConcurrentHashMap<String, String>();

    //处理消息的方法
    public static Map<String,Object> dealMessage(Session session,String message){
        Map<String,Object> map = new HashMap<String, Object>();

        JSONObject json = JSONObject.fromObject(message);
        JSONObject data = json.getJSONObject("data");
        String act = json.getString("act");

        //初次加入 data: ip city
        if(act.equals("join")){
            nickMap.put(session.getId(),"来自"+data.getString("city")+"的网友（"+data.getString("ip")+")");
            map.put("act","join");
            map.put("nick",nickMap.get(session.getId()));
            map.put("pNum",onlineNum);    //当前在线人数
            map.put("nickMap",nickMap);
        }

        return map;
    }

    public static int getOnlineNum(){
        return onlineNum;
    }

    public static synchronized int addOnlineNum() {
        return ++onlineNum;
    }

    public static synchronized int subOnlineNum() {
        return --onlineNum;
    }

    public static ConcurrentHashMap<String, String> getNickMap() {
        return nickMap;
    }

}
