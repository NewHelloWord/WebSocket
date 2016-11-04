package chat;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by JerryMouse on 2016/11/5.
 */
public class OpenServer {

    private static CopyOnWriteArraySet<WebSocketTest> webSet = new CopyOnWriteArraySet<WebSocketTest>();

    public static CopyOnWriteArraySet<WebSocketTest> getWebSet() {
        return webSet;
    }


}
