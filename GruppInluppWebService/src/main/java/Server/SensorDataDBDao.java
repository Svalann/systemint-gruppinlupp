package Server;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import Classes.SensorData;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorDataDBDao {

    private static final Properties prop = new Properties();
    private final String url;
    Connection connection = null;

    public SensorDataDBDao() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            /*Viktor*/  
            prop.load(new FileInputStream("C:\\Users\\vikto\\OneDrive\\Nackademin IoT\\SystemIntegration GruppInlupp\\GruppInluppWebService\\src\\main\\java\\Server\\settings.properties"));
            /*Tolga*/   
            //prop.load(new FileInputStream(""));
            /*Robert*/  
            //prop.load(new FileInputStream(""));
            /*Tobias*/  
            //prop.load(new FileInputStream(""));
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SensorDataDBDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String hostName = prop.getProperty("hostName"); 
        String dbName = prop.getProperty("dbName"); 
        String user = prop.getProperty("user"); 
        String password = prop.getProperty("password"); 
        this.url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
    }

    public SensorData getLatestData() {

        SensorData data = new SensorData();
         try {
            connection = DriverManager.getConnection(url);

            PreparedStatement stmt = connection.prepareStatement("SELECT TOP 1 [Id],[Temperature],[Humidity],[Created] FROM [dbo].[SensorData] ORDER BY [Created] DESC");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                float temperature = rs.getFloat("temperature");
                float humidity = rs.getFloat("humidity");
                Date created = rs.getTimestamp("created");

                data.setTemperature(temperature);
                data.setHumidity(humidity);
                data.setCreated(created);

            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }

    public List<SensorData> getAll() {

        List<SensorData> list = new ArrayList<>();
        //SensorData data = new SensorData();

        try (Connection con = DriverManager.getConnection(url)) {

            PreparedStatement stmt = con.prepareStatement("SELECT TOP 2500 [Id],[Temperature],[Humidity],[Created] FROM [dbo].[SensorData]");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                float temperature = rs.getFloat("temperature");
                float humidity = rs.getFloat("humidity");
                Date created = rs.getTimestamp("created");
                int id = rs.getInt("id");                

                list.add(new SensorData(id, temperature, humidity, created));

            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
