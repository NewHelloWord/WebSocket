package chat;

import net.sf.json.JSONObject;

import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by JerryMouse on 2016/10/19.
 */
@ServerEndpoint("/chat")
public class WebSocketTest {

    private String CREATE_USER_NICK = "/CREATE_USER_NICK/";

    //private static int onlineNum = 0;  //当前在线数

    //private static CopyOnWriteArraySet<WebSocketTest> webSet = new CopyOnWriteArraySet<WebSocketTest>();

    //private static ConcurrentHashMap<String, String> nickMap = new ConcurrentHashMap<String, String>();

    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        OpenServer.getWebSet().add(this);
        int num = CoreServer.addOnlineNum();
        //webSet.add(this);
        //addOnlineNum();
        System.out.println("新连接，当前在线数："+num);
    }

    @OnClose
    public void onClose(Session session){
        OpenServer.getWebSet().remove(this);
        int num = CoreServer.subOnlineNum();
        OpenServer.getWebSet().remove(this);
        CoreServer.getNickMap().remove(session.getId());
        //webSet.remove(this);  //从set中删除
        //subOnlineNum();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + num);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("act","back");
        map.put("sid",session.getId());
        map.put("pNum",CoreServer.getOnlineNum());
        JSONObject json = JSONObject.fromObject(map);
        send(json);
//        for(WebSocketTest wt : OpenServer.getWebSet()){
//            try {
//                if(!wt.session.equals(this.session)){
//                    wt.sendMessage(json.toString());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误了。。。。。");
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message,Session session){

        System.out.println("收到的客户端的消息："+message);

        Map<String,Object> map = CoreServer.dealMessage(session,message);
        JSONObject json = JSONObject.fromObject(map);
        System.out.println("onMessage========"+json);
        send(json);
//        for(WebSocketTest wt : OpenServer.getWebSet()){
//            try {
//                if(!wt.session.equals(this.session)){
//                    wt.sendMessage(json.toString());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        JSONObject json = JSONObject.fromObject(message);
//        System.out.println(json.getString("act"));
//        System.out.println(json.getJSONObject("data").getString("ip"));
//        System.out.println(json.getJSONObject("data").getString("city"));
//
//
//        Map<String,String> map = new HashMap<String, String>();
//        if(nickMap.get(session.getId()) == null || nickMap.get(session.getId()).equals("无名")){
//            if(message.contains(CREATE_USER_NICK)){
//                nickMap.put(session.getId(),message.replace(CREATE_USER_NICK,""));
//                map.put("nick",message.replace(CREATE_USER_NICK,""));
//                map.put("message","大家好，我是"+message.replace(CREATE_USER_NICK,""));
//            }else {
//                nickMap.put(session.getId(),"无名");
//                map.put("nick","无名");
//                map.put("message",message);
//            }
//        }else {
//            map.put("nick",nickMap.get(session.getId()));
//            map.put("message",message);
//        }
//
//
//        //JSONObject json = JSONObject.fromObject(map);
//        System.out.println(json.toString()+"-------------------");
//        for(WebSocketTest wt : webSet){
//            try {
//                if(this.session!=wt.session){
//                    wt.sendMessage(json.toString());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }



    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void send(JSONObject json){
        for(WebSocketTest wt : OpenServer.getWebSet()){
            try {
                if(!wt.session.equals(this.session)){
                    wt.sendMessage(json.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static synchronized int addOnlineNum() {
//        return ++onlineNum;
//    }
//
//    public static synchronized int subOnlineNum() {
//       return --onlineNum;
//    }

}
