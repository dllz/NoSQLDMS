package rest.service;

import db.data.structures.position.PositionList;
import db.data.structures.tree.TreeArray;
import db.models.tree.EntryKeys;
import db.models.tree.NodeKey;
import org.codehaus.jackson.map.ObjectMapper;
import rest.service.models.ApiResponse;
import rest.service.models.ReponseCodes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
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
	public static String putLeft(@PathParam("treekey") String treekey, @PathParam("rightkey") String rightkey, @PathParam("parentkey") String parentkey, @PathParam("value") String value, @PathParam("key") String key)
	{
		trees.addLeft(treekey, new EntryKeys(parentkey, null, rightkey, key), value);
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse(null));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	@GET
	@Path("putright/tree={treekey}/left={leftkey}&parent={parentkey}/key={key}&value={value}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String putRight(@PathParam("treekey") String treekey, @PathParam("leftkey") String leftkey, @PathParam("parentkey") String parentkey, @PathParam("value") String value, @PathParam("key") String key)
	{
		trees.addRight(
				treekey, new EntryKeys(parentkey, leftkey, null, key), value);
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse(null));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	@GET
	@Path("putroot/tree={treekey}/key={key}&value={value}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String putRoot(@PathParam("treekey") String treekey, @PathParam("value") String value, @PathParam("key") String key)
	{
		ObjectMapper map = new ObjectMapper();
		trees.addRoot(treekey, new EntryKeys(null, null, null, key), value);
		try
		{
			return map.writeValueAsString(new ApiResponse(null));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	@GET
	@Path("search/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String search(@PathParam("key") String key)
	{
		ObjectMapper map = new ObjectMapper();
		PositionList<NodeKey> list = trees.search(key);
		if (list == null)
		{
			try
			{
				return map.writeValueAsString(new ApiResponse(new NodeKey[0], ReponseCodes.NOT_FOUND));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		NodeKey[] arry = new NodeKey[list.size()];
		Iterator<NodeKey> listit = list.iterator();
		int count = 0;
		while (listit.hasNext())
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
	@Path("get/treekey={treekey}&nodekey={nodekey}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String getElement(@PathParam("treekey") String tkey, @PathParam("nodekey") String nodekey)
	{
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse(trees.getElement(new NodeKey(tkey, nodekey))));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}


	@GET
	@Path("transversal/inorder/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String inOrder(@PathParam("key") String key)
	{
		ObjectMapper map = new ObjectMapper();
		PositionList<String> list = trees.inOrderTraversal(key);
		if (list == null)
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
		while (listit.hasNext())
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
	@Path("transversal/preorder/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String preOrder(@PathParam("key") String key)
	{
		ObjectMapper map = new ObjectMapper();
		PositionList<String> list = trees.PreOrderTraversal(key);
		if (list == null)
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
		while (listit.hasNext())
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
	@Path("transversal/postorder/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String postOrder(@PathParam("key") String key)
	{
		ObjectMapper map = new ObjectMapper();
		PositionList<String> list = trees.PostOrderTraversal(key);
		if (list == null)
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
		while (listit.hasNext())
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
	@Path("get/tree/{treekey}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String getTree(@PathParam("treekey") String tkey)
	{
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse(trees.getTree(tkey)));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

}
