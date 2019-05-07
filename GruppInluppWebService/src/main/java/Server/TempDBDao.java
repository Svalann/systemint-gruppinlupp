package Server;


import Classes.TempClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TempDBDao {
    
    public TempDBDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TempDBDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TempClass> getLatestTemp(){
        
        List<TempClass> list = new ArrayList<>();
        
        try(Connection con = DriverManager.getConnection("url", "user", "password")) {
            
            PreparedStatement stmt = con.prepareStatement("select temperature from ServerNamn"); // change ServerNamn to schema name
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                float temperature = rs.getFloat("temperature");
                
                list.add(new TempClass(temperature));
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
    
}
