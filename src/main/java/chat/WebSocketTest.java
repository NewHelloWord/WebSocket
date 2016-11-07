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
        System.out.println("有一连接关闭！当前在线人数为" + num);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("act","back");
        map.put("sid",session.getId());
        map.put("pNum",CoreServer.getOnlineNum());
        send(map);
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
        send(map);
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void send(Map<String,Object> map){
        JSONObject json = JSONObject.fromObject(map);
        System.out.println("onMessage========"+json);
        try{
            if(map.get("act").equals("changeNick")){
                for(WebSocketTest wt : OpenServer.getWebSet()){
                    wt.sendMessage(json.toString());
                }
            }else{
                for(WebSocketTest wt : OpenServer.getWebSet()){
                    if(!wt.session.equals(this.session)){
                        wt.sendMessage(json.toString());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
