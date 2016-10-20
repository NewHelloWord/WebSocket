package chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by JerryMouse on 2016/10/19.
 */
@ServerEndpoint("/chat")
public class WebSocketTest {

    private static int onlineNum = 0;  //当前在线数

    private static CopyOnWriteArraySet<WebSocketTest> webSet = new CopyOnWriteArraySet<WebSocketTest>();

    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSet.add(this);
        addOnlineNum();
        System.out.println("新连接，当前在线数："+onlineNum);
    }

    @OnClose
    public void onClose(){
        webSet.remove(this);  //从set中删除
        subOnlineNum();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + onlineNum);
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误了。。。。。");
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("收到的客户端的消息："+message);
        for(WebSocketTest wt : webSet){
            try {
                if(this.session!=wt.session){
                    wt.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static synchronized void addOnlineNum() {
        onlineNum++;
    }

    public static synchronized void subOnlineNum() {
        onlineNum--;
    }

}
