package rest;

import db.data.persistance.HtPersistance;
import rest.service.hash;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import rest.service.check;

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
        hash.setHt(HtPersistance.readht());
        ResourceConfig rc = new ResourceConfig();
        rc.registerClasses(hash.class);
        rc.registerClasses(check.class);
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        hash.save();
        server.stop();
    }

}
