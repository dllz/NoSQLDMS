package rest.service;

import db.data.structures.tree.TreeArray;
import rest.service.models.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by danie on 2017/04/25.
 */
@Path("hash/")
public class tree
{
    private static TreeArray tree = new TreeArray();

    @GET
    @Path("save/")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse save()
    {
        //TreePersistance.save(tree);
        return new ApiResponse("Saved");
    }
    public static void setTree(TreeArray te)
    {
        tree = te;
    }
}
