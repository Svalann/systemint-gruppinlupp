package Service;

import Classes.SensorData;
import Server.SensorDataDBDao;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/SensorDataService")
public class SensorDataService {

    private static SensorDataDBDao dao = new SensorDataDBDao();

   
    
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
    
        @GET
    @Path("/TempSensor/{idFrom}/{idTo}")
    @Produces (MediaType.APPLICATION_JSON) 
    public List<SensorData> getDataByIdIntervall(@PathParam("idFrom") int idFrom, @PathParam("idTo") int idTo){
            
        return dao.getIntervall(idFrom, idTo);
    }
    

}
