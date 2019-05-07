 package Server;


import Classes.DAO;
import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import Classes.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/tempsensor/connect", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class SensorEndpoint {
    private Session session;
    private static DAO d = new DAO();

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        
        this.session = session;
        Message m = new Message();
        int i = 0;
        
        while(true){
            try {
                Thread.sleep(3000);
                m.setTemperature(""+d.method()+ i);
                session.getBasicRemote().sendObject(m);
                i++;                
            } catch (InterruptedException ex) {
                Logger.getLogger(SensorEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
}
