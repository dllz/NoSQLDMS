package rest.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

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
        try
        {
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
            server.start();
        }
        catch (NoSuchMethodError e)
        {
            System.err.println(e.toString());
        }


    }

}
