
package WebService;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;
@Path ("Service")


public class Service {
    private DaoTemperature tempdao= new DaoTemperature();
    
     @GET
     @Path("/table")
     @Produces (MediaType.APPLICATION_JSON)
     public  void getTable(){
         
     }
}
