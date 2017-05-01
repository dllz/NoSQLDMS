package rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by danie on 2017/04/22.
 */
@Path("api/")
public class check
{
	@GET
	@Path("check")
	@Produces(MediaType.TEXT_PLAIN)
	public static String check()
	{
		System.out.println("Check Called");
		return "Api Running";
	}
}
