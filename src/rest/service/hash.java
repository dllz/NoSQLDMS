package rest.service;

import db.data.structures.hash.HashTable;
import db.data.structures.position.PositionList;
import db.models.hash.HashField;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import rest.service.models.ApiResponse;
import rest.service.models.ReponseCodes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.Iterator;


/**
 * Created by danie on 2017/04/18.
 */
@Path("hash/")
public class hash
{
    private static HashTable<String> ht = new HashTable<>();

    @GET
    @Path("searchv/{field}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String searchfv(@PathParam("field") String field, @PathParam("value") String value) {
        PositionList<String> list = ht.search(field, value);
		ObjectMapper map = new ObjectMapper();
        if(list == null)
        {
			try
			{
				return map.writeValueAsString(new ApiResponse(new String[0], ReponseCodes.NOT_FOUND));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
        String[] arry = new String[list.size()];
        Iterator<String> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
		try
		{
			return map.writeValueAsString(new ApiResponse(arry));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

    @GET
    @Path("searchv/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String searchv(@PathParam("value") String value) {
        PositionList<String> list = ht.search(value);
		ObjectMapper map = new ObjectMapper();
        if(list == null)
        {
			try
			{
				return map.writeValueAsString(new ApiResponse(new String[0], ReponseCodes.NOT_FOUND));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
        String[] arry = new String[list.size()];
        Iterator<String> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
		try
		{
			return map.writeValueAsString(new ApiResponse(arry));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

    @GET
    @Path("put/{key}/{field}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String hashput(@PathParam("key") String key, @PathParam("field") String field, @PathParam("value") String value) {
    	HashField temp = new HashField(field, value);
		boolean res = ht.put(key, temp);
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse("Completed"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

    @GET
    @Path("del/{key}/{field}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String hashdel(@PathParam("key") String key, @PathParam("field") String field) {
        HashField res = ht.remove(key, field);
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString( new ApiResponse(res));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return"";
    }

    @GET
    @Path("get/{key}/{field}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String hashget(@PathParam("key") String key, @PathParam("field") String field) {
		ObjectMapper map = new ObjectMapper();
       try
	   {
		   HashField res = ht.get(key, field);
		   return  map.writeValueAsString(new ApiResponse(res));
	   }
	   catch(NullPointerException e)
	   {

		   try
		   {
			   return map.writeValueAsString(new ApiResponse(null, ReponseCodes.NOT_FOUND));
		   } catch (IOException e1)
		   {
			   e1.printStackTrace();
		   }
	   } catch (JsonGenerationException e)
	   {
		   e.printStackTrace();
	   } catch (JsonMappingException e)
	   {
		   e.printStackTrace();
	   } catch (IOException e)
	   {
		   e.printStackTrace();
	   }
	   return "";
	}

    @GET
    @Path("getall/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String hashgetall(@PathParam("key") String key) {
        PositionList<HashField> list = ht.getAll(key);
		ObjectMapper map = new ObjectMapper();
        if(list == null)
        {
			try
			{
				return  map.writeValueAsString(new ApiResponse(new HashField[0], ReponseCodes.NOT_FOUND));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
        HashField[] arry = new HashField[list.size()];
        Iterator<HashField> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
		try
		{
			return map.writeValueAsString(new ApiResponse(arry));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

}
