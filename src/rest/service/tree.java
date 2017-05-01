package rest.service;

import db.data.structures.position.PositionList;
import db.data.structures.tree.TreeArray;
import db.models.tree.EntryKeys;
import db.models.tree.NodeKey;
import rest.service.models.ApiResponse;
import rest.service.models.ReponseCodes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Iterator;

/**
 * Created by danie on 2017/04/25.
 */
@Path("hash/")
public class tree
{
    private static TreeArray trees = new TreeArray();

    @GET
    @Path("putleft/tree={treekey}/right={rightkey}&parent={parentkey}/key={key}&value={value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse putLeft(@PathParam("treekey") String treekey,  @PathParam("rightkey") String rightkey, @PathParam("parentkey") String parentkey, @PathParam("value") String value, @PathParam("key") String key)
    {
        trees.addLeft(treekey, new EntryKeys(parentkey, null, rightkey, key), value);
        return new ApiResponse(null);
    }

    @GET
    @Path("putright/tree={treekey}/left={leftkey}&parent={parentkey}/key={key}&value={value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse putRight(@PathParam("treekey") String treekey, @PathParam("leftkey") String leftkey, @PathParam("parentkey") String parentkey, @PathParam("value") String value, @PathParam("key") String key)
    {
        trees.addRight(
                treekey, new EntryKeys(parentkey, leftkey, null, key), value);
        return new ApiResponse(null);
    }

    @GET
    @Path("putroot/tree={treekey}/key={key}&value={value}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse putRoot(@PathParam("treekey") String treekey, @PathParam("value") String value, @PathParam("key") String key)
    {
        trees.addRoot(treekey, new EntryKeys(null, null, null, key), value);
        return new ApiResponse(null);
    }

    @GET
    @Path("search/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse search(@PathParam("key") String key)
    {
        PositionList<NodeKey> list = trees.search(key);
        if(list == null)
        {
            return new ApiResponse(new NodeKey[0], ReponseCodes.NOT_FOUND);
        }
        NodeKey[] arry = new NodeKey[list.size()];
        Iterator<NodeKey> listit = list.iterator();
        int count = 0;
        while(listit.hasNext())
        {
            arry[count] = listit.next();
            count++;
        }
        return new ApiResponse(arry);
    }

    @GET
    @Path("get/treekey={treekey}&nodekey={nodekey}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse getElement(@PathParam("treekey") String tkey, @PathParam("nodekey") String nodekey)
    {
        return new ApiResponse(trees.getElement(new NodeKey(tkey, nodekey)));
    }


    @GET
    @Path("transversal/inorder/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse inOrder(@PathParam("key") String key)
    {
        PositionList<String> list = trees.inOrderTraversal(key);
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
    @Path("transversal/preorder/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse preOrder(@PathParam("key") String key)
    {
        PositionList<String> list = trees.PreOrderTraversal(key);
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
    @Path("transversal/postorder/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse postOrder(@PathParam("key") String key)
    {
        PositionList<String> list = trees.PostOrderTraversal(key);
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
    @Path("get/tree/{treekey}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ApiResponse getTree(@PathParam("treekey") String tkey)
    {
        return new ApiResponse(trees.getTree(tkey));
    }

    public static void setTree(TreeArray te)
    {
        trees = te;
    }
}
