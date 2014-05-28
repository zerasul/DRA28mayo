package es.ual.unia.rest.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/suma")
public class ExampleRest {

	@Path("/{a}/{b}/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int sumar(@PathParam("a")int a, @PathParam("b")int b)
	{
		return a+b;
	}
}
