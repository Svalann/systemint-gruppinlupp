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
import Classes.SensorData;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/tempsensor/connect", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class SensorEndpoint {
    private Session session;
    private static SensorDataDBDao dao = new SensorDataDBDao();

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        
        this.session = session;
        Message messageToSend = new Message();
        
        while(true){
            try {
                SensorData latestData = dao.getLatestData();
                
                messageToSend.setTemperature(latestData.getTemperature());
                messageToSend.setHumidity(latestData.getHumidity());
                messageToSend.setId(latestData.getId());
                
                Date date = latestData.getCreated();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.HOUR_OF_DAY, 2); // adds four hour
                date = cal.getTime();                
                messageToSend.setCreated(date);                
                
                session.getBasicRemote().sendObject(messageToSend);
                Thread.sleep(3000);
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
