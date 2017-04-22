package rest.service;

import db.data.persistance.HtPersistance;
import db.data.structures.hash.HashTable;
import db.data.structures.hash.position.PositionList;
import db.models.hash.HashField;
import rest.service.models.ApiResponse;
import rest.service.models.ReponseCodes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Iterator;


/**
 * Created by danie on 2017/04/18.
 */
@Path("hash/")
public class hash
{
    private static HashTable<String> ht = null;

    @GET
    @Path("searchv/{field}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse searchfv(@PathParam("field") String field, @PathParam("value") String value) {
        PositionList<String> list = ht.search(field, value);
        if(list == null)
        {
            return new ApiResponse(new String[0], ReponseCodes.NOT_FOUND);
        }
        String[] arry = new String[list.size()];
        Iterator<String> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
        return new ApiResponse(arry);

    }

    @GET
    @Path("searchv/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse searchv(@PathParam("value") String value) {
        PositionList<String> list = ht.search(value);
        if(list == null)
        {
            return new ApiResponse(new String[0], ReponseCodes.NOT_FOUND);
        }
        String[] arry = new String[list.size()];
        Iterator<String> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
        return new ApiResponse(arry);
    }

    @PUT
    @Path("put/{key}/{field}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse hashput(@PathParam("key") String key, @PathParam("field") String field, @PathParam("value") String value) {
        ht.put(key, new HashField(field, value));
        return new ApiResponse("Completed");
    }

    @GET
    @Path("del/{key}/{field}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse hashdel(@PathParam("key") String key, @PathParam("field") String field) {
        HashField res = ht.remove(key, field);
        return new ApiResponse(res);
    }

    @GET
    @Path("get/{key}/{field}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse hashget(@PathParam("key") String key, @PathParam("field") String field) {
        HashField res = ht.get(key, field);
        return new ApiResponse(res);
    }

    @GET
    @Path("getall/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse hashgetall(@PathParam("key") String key) {
        PositionList<HashField> list = ht.getAll(key);
        if(list == null)
        {
            return new ApiResponse(new HashField[0], ReponseCodes.NOT_FOUND);
        }
        HashField[] arry = new HashField[list.size()];
        Iterator<HashField> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
        return new ApiResponse(arry);
    }

    public static void setHt(HashTable<String> ht)
    {
        hash.ht = ht;
    }

    @GET
    @Path("save/")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse save()
    {
        HtPersistance.saveht(ht);
        return new ApiResponse("Saved");
    }


}
