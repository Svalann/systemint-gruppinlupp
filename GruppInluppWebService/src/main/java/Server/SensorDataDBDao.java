package Server;

import Classes.SensorData;
import Classes.SensorData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorDataDBDao {

    static Properties prop = new Properties();

    public SensorDataDBDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            prop.load(new FileInputStream("C:\\Users\\vikto\\OneDrive\\Nackademin IoT\\SystemIntegration GruppInlupp\\GruppInluppWebService\\src\\main\\java\\Server\\settings.properties"));
            //prop.load(new FileInputStream("C:\\Users\\andic\\OneDrive\\Dokument\\NetBeansProjects\\systemint-gruppinlupp\\GruppInluppWebService\\settings.properties"));
            //prop.load(new FileInputStream("C:\\Users\\vikto\\OneDrive\\Nackademin IoT\\SystemIntegration GruppInlupp\\GruppInluppWebService\\src\\main\\java\\Server\\settings.properties"));
            //prop.load(new FileInputStream("C:\\Users\\vikto\\OneDrive\\Nackademin IoT\\SystemIntegration GruppInlupp\\GruppInluppWebService\\src\\main\\java\\Server\\settings.properties"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SensorDataDBDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SensorDataDBDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SensorDataDBDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SensorData getLatestData() {

        //List<SensorData> list = new ArrayList<>();
        SensorData data = new SensorData();

        try (Connection con = DriverManager.getConnection(prop.getProperty("connectionString"),
                 prop.getProperty("name"), prop.getProperty("password"))) {

            PreparedStatement stmt = con.prepareStatement("select temperature, humidity, created from innodb.SensorData order by created desc limit 1");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                float temperature = rs.getFloat("temperature");
                float humidity = rs.getFloat("humidity");
                Date created = rs.getTimestamp("created");

                data.setTemperature(temperature);
                data.setHumidity(humidity);
                data.setCreated(created);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public List<SensorData> getAll() {

        List<SensorData> list = new ArrayList<>();
        //SensorData data = new SensorData();

        try (Connection con = DriverManager.getConnection(prop.getProperty("connectionString"),
                 prop.getProperty("name"), prop.getProperty("password"))) {

            PreparedStatement stmt = con.prepareStatement("select temperature, humidity, created from innodb.SensorData");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                float temperature = rs.getFloat("temperature");
                float humidity = rs.getFloat("humidity");
                Date created = rs.getTimestamp("created");

                list.add(new SensorData(temperature, humidity, created));

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
