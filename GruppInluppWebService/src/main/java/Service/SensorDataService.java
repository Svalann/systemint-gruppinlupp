package Service;

import Classes.SensorData;
import Server.SensorDataDBDao;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/SensorDataService")
public class SensorDataService {

    private static SensorDataDBDao dao = new SensorDataDBDao();

    @GET
    @Path("/hi")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {

        return "Hej, det funkar";
        
    }
    
    @GET
    @Path("/TempSensor/getLatestData")
    @Produces(MediaType.APPLICATION_JSON)
    public SensorData getLatestData(){
        
        return dao.getLatestData();
        
    }
    
    @GET
    @Path("/TempSensor/getAllData")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorData> getAll(){
        
        return dao.getAll();
        
    }
    
    

}
