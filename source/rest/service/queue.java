package rest.service;

import db.data.structures.queue.Queue;
import db.interfaces.position.Position;
import org.codehaus.jackson.map.ObjectMapper;
import rest.service.models.ApiResponse;
import rest.service.models.ReponseCodes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by danie on 2017/05/01.
 */
@Path("enqueue/")
public class queue
{
	private static Queue q = new Queue();

	@GET
	@Path("enqueue/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String enqueue(@PathParam("value") String value)
	{
		System.out.println("enqueue Called");
		q.qeueu(value);
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse("Enqueued"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	@GET
	@Path("dequeue")
	@Produces(MediaType.APPLICATION_JSON)
	public static String dequeue()
	{
		System.out.println("dequeue Called");
		ObjectMapper map = new ObjectMapper();
		try
		{
			String temp = q.dequeue();
			if (temp != null)
			{
				return map.writeValueAsString(new ApiResponse(temp));
			}
			return map.writeValueAsString(new ApiResponse(null, ReponseCodes.EMPTY));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
