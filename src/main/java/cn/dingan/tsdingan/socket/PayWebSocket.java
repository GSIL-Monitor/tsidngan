package cn.dingan.tsdingan.socket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: PayWebSocket
* @Description: 支付完成消息推送
* @author jyq#trasen.cn
* @date 2019年2月19日 下午1:47:24
*
 */
@ServerEndpoint(value = "/paywebsocket/{id}")
@Component
public class PayWebSocket {

    
    private static int onlineCount = 0;

    
    private static ConcurrentHashMap<String, PayWebSocket> webSocketSet = new ConcurrentHashMap<>();
    
    private Session session;
    
    private String id = "";

    @OnOpen
    public void onOpen(@PathParam(value = "id") String id,Session session) {
        this.session = session;
        webSocketSet.put(id, this);     //加入set中
        this.id = id;
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount()+",用户id为======="+id);
        try {
            sendMessage("链接成功");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }
    
    @OnClose
    public void onClose(@PathParam(value = "id") String id) {
        webSocketSet.remove(id);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    
    
    /**
     * 
    * @Title: sendtoUser
    * @Description: 发送用户
    * @param @param message
    * @param @param sendUserId
    * @param @throws IOException    参数
    * @return void    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月19日 下午2:11:05
     */
    public void sendtoUser(String message,String sendUserId) throws IOException {
        if (webSocketSet.get(sendUserId) != null) {
            
            webSocketSet.get(sendUserId).sendMessage(message);
            
        } else {
            //如果用户不在线则返回不在线信息给自己
//            sendtoUser("当前用户不在线",id);
        }
    }
 
    
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    
    

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        PayWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        PayWebSocket.onlineCount--;
    }
}
