package rest.service;

import db.data.structures.position.PositionList;
import db.data.structures.queue.Queue;
import rest.service.models.ApiResponse;
import rest.service.models.ReponseCodes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Iterator;

/**
 * Created by danie on 2017/05/01.
 */
@Path("queue/")
public class queue
{
	private static Queue q = new Queue();

	@GET
	@Path("enqueue/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public static ApiResponse queue(@PathParam("value") String value) {
		q.qeueu(value);
		return new ApiResponse("Enqueued");
	}

	@GET
	@Path("dequeue")
	@Produces(MediaType.APPLICATION_JSON)
	public static ApiResponse dequeue() {
		return new ApiResponse(q.dequeue());
	}
}
