package chat;

import net.sf.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        OpenManager.getWebSet().add(this);
        int num = CoreServer.addOnlineNum();
        //webSet.add(this);
        //addOnlineNum();
    }

    @OnClose
    public void onClose(Session session){
        OpenManager.getWebSet().remove(this);
        int num = CoreServer.subOnlineNum();
        OpenManager.getWebSet().remove(this);
        CoreServer.getNickMap().remove(session.getId());

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("act","back");
        map.put("sid",session.getId());
        map.put("pNum",CoreServer.getOnlineNum());
        send(map);
    }

    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message,Session session){
        Map<String,Object> map = CoreServer.dealMessage(session,message);
        send(map);
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void send(Map<String,Object> map){
        JSONObject json = JSONObject.fromObject(map);
        try{
            if(map.get("act").equals("changeNick")){
                for(WebSocketTest wt : OpenManager.getWebSet()){
                    wt.sendMessage(json.toString());
                }
            }else{
                for(WebSocketTest wt : OpenManager.getWebSet()){
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
