package rest.service;

import db.data.structures.hash.position.PositionList;
import db.data.structures.hash.position.PositionListIterator;
import db.models.hash.HashField;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @Produces(MediaType.APPLICATION_JSON)
    public String[] searchfv(String field, Object value) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public String[] searchv(Object value) {
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
    @Produces(MediaType.TEXT_PLAIN)
    public String hashput(String key, HashField value) {
        ht.put(key, value);
        return "Completed";
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public HashField hashdel(String key, String field) {
        return ht.remove(key, field);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashField hashget(String key, String field) {
        return ht.get(key, field);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashField[] hashgetall(String key) {
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
