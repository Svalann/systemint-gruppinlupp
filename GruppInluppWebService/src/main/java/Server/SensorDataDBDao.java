package Server;


import Classes.SensorData;
import Classes.SensorData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SensorDataDBDao {
    
    public SensorDataDBDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SensorDataDBDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SensorData> getLatestTemp(){ //ej list? bara SensorData?
        
        List<SensorData> list = new ArrayList<>();
        
        try(Connection con = DriverManager.getConnection("url", "user", "password")) {
            
            PreparedStatement stmt = con.prepareStatement("select temperature, humidity, created from SensorData"); // change ServerNamn to schema name
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                float temperature = rs.getFloat("temperature");
                float humidity = rs.getFloat("humidity");
                Date created = rs.getTimestamp("created");
                SensorData data = new SensorData();
                data.setTemperature(temperature);
                data.setHumidity(humidity);
                data.setCreated(created);
                list.add(data);
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
    
}
