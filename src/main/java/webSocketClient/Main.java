package webSocketClient;

import org.springframework.web.socket.WebSocketSession;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        WebSocketOperations webSocketOperations = new WebSocketOperations();

        WebSocketSession session = webSocketOperations.connectSession();
        Thread.sleep(3000);
        webSocketOperations.sendMessage(session, "HelloWorld!");
        Thread.sleep(3000);
        webSocketOperations.closeSession(session);

        webSocketOperations.getMessages();
    }
}

