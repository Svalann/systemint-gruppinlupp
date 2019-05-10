
package WebService;

import Classes.SensorData;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
public class DaoTemperature {
    Connection con;
    private static final Properties prop = new Properties();
    private String url;
   public void DaoTemperature(){
       try{
        Class.forName("com.mysql.jdbc.Driver");
        
        prop.load(new FileInputStream("C:\\Users\\tobbe\\OneDrive\\Dokument\\NetBeansProjects\\systemint-gruppinlupp\\GruppInluppWebService\\src\\main\\java\\WebService"));
                
         }  
        catch (Exception e){
        e.printStackTrace();
               }
        String hostName = prop.getProperty("hostName"); 
        String dbName = prop.getProperty("dbName"); 
        String user = prop.getProperty("user"); 
        String password = prop.getProperty("password"); 
        this.url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        
    }
   public List<SensorData> getTableData(){
       List<SensorData> list = new ArrayList<>();
        //SensorData data = new SensorData();

        try (Connection con = DriverManager.getConnection(url)) {

            PreparedStatement stmt = con.prepareStatement("\"SELECT TOP 100 [Id],[Temperature],[Humidity],[Created] FROM [dbo].[SensorData] ORDER BY [Created] DESC\"");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                float temperature = rs.getFloat("temperature");
                float humidity = rs.getFloat("humidity");
                Date created = rs.getTimestamp("created");

                list.add(new SensorData(temperature, humidity, created));

            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
}
