package webSocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/refresh")
public class PreparationPartieSocket{
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());


    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        session.setMaxIdleTimeout(0);
        System.err.println(String.format("%s",  session));

    }
    @OnMessage
      public void onMessage(String message, Session session) throws IOException {
        for (Session client: clients) {
          client.getBasicRemote().sendText(message);
          System.out.println(client.getId());
        }
      }
    
    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
    
    @OnClose
    public void onClose(Session session) {
        System.err.println("Removing " + session);
        clients.remove(session);
    }
}