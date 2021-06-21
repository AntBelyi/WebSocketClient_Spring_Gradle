package webSocketClient;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;

public class WebSocketOperations {
    private static final String URI = System.getenv("WS_ENDPOINT");
    private EventHandler socket = new EventHandler();

    public WebSocketSession connectSession() {
        WebSocketSession session = null;

        try {
            StandardWebSocketClient client = new StandardWebSocketClient();

            //Make a handshake with server
            ListenableFuture<WebSocketSession> handShake = client.doHandshake(socket, URI);

            //Wait for connect
            session = handShake.get();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
        return session;
    }

    public void sendMessage(WebSocketSession session, String message) {
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeSession(WebSocketSession session) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessages() {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Count of messages: " + socket.getWebSocketListener().size());
        System.out.println(socket.getWebSocketListener().get(2));
        System.out.println(socket.getWebSocketListener().get(socket.getWebSocketListener().size() - 1));
    }
}
