 package Server;


import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import Classes.Message;

@ServerEndpoint(value = "/tempsensor", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
    private Session session;

    @OnOpen
    public void onOpen(Session session) 
            throws IOException, EncodeException {
        this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        
    }
    
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) 
            throws IOException, EncodeException {
        
    }

}
