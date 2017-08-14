package smart.quickhelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/api/test")
public class Test {
	@GET
	@Produces("text/plain")

	public String getData() {
		return "success if you see this";
		}
}
