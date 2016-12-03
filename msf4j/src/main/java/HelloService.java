import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class HelloService {

    @GET
    public String hello() {
        return "Hello World!";
    }

}