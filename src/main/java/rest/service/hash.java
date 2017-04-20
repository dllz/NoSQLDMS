package rest.service;

import db.data.structures.hash.position.PositionList;
import db.models.hash.HashField;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Iterator;

import static rest.service.Main.ht;

/**
 * Created by danie on 2017/04/18.
 */
@Path("/hash")
public class hash
{
    @GET
    @Path("searchv/{field}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] searchfv(@PathParam("field") String field, @PathParam("value") String value) {
        PositionList<String> list = ht.search(field, value);
        String[] arry = new String[list.size()];
        Iterator<String> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
        return arry;

    }

    @GET
    @Path("searchv/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] searchv(@PathParam("value") String value) {
        PositionList<String> list = ht.search(value);
        String[] arry = new String[list.size()];
        Iterator<String> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
        return arry;
    }

    @PUT
    @Path("put/{key}/{field}/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hashput(@PathParam("key") String key, @PathParam("field") String field, @PathParam("value") String value) {
        ht.put(key, new HashField(field, value));
        return "Completed";
    }

    @GET
    @Path("del/{key}/{field}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashField hashdel(@PathParam("key") String key, @PathParam("field") String field) {
        return ht.remove(key, field);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashField hashget(String key, String field) {
        return ht.get(key, field);
    }

    @GET
    @Path("getall/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashField[] hashgetall(@PathParam("key") String key) {
        PositionList<HashField> list = ht.getAll(key);
        HashField[] arry = new HashField[list.size()];
        Iterator<HashField> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
        return arry;
    }


}
