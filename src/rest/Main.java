package rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import rest.service.check;
import rest.service.hash;
import rest.service.queue;
import rest.service.tree;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by danie on 2017/04/10.
 */
public class Main
{
	public static final URI BASE_URI = UriBuilder
			.fromUri("http://localhost/")
			.port(8000)
			.build();

	public static void main(String[] args) throws URISyntaxException, IOException
	{
		ResourceConfig rc = new ResourceConfig();
		rc.registerClasses(hash.class);
		rc.registerClasses(check.class);
		rc.register(tree.class);
		rc.register(queue.class);
		final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
		System.out.println(String.format("Jersey app started with WADL available at "
				+ "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
		System.in.read();
		server.stop();
	}

}
