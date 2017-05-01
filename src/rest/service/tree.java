package rest.service;

import db.data.structures.position.PositionList;
import db.data.structures.tree.TreeArray;
import db.models.tree.EntryKeys;
import db.models.tree.KeyValue;
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
@Path("tree/")
public class tree
{
	private static TreeArray trees = new TreeArray();

	@GET
	@Path("putleft/{treekey}/{rightkey}/{parentkey}/{key}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
		public static String putLeft(@PathParam("treekey") String treekey, @PathParam("rightkey") String rightkey, @PathParam("parentkey") String parentkey, @PathParam("value") String value, @PathParam("key") String key)
	{
		System.out.println("putleft Called");
		String res = trees.addLeft(treekey, new EntryKeys(parentkey, null, rightkey, key), value);
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse(res));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return res;
	}

	@GET
	@Path("putright/{treekey}/{leftkey}/{parentkey}/{key}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String putRight(@PathParam("treekey") String treekey, @PathParam("leftkey") String leftkey, @PathParam("parentkey") String parentkey, @PathParam("value") String value, @PathParam("key") String key)
	{
		System.out.println("putright Called");
		String res = trees.addRight(
				treekey, new EntryKeys(parentkey, leftkey, null, key), value);
		ObjectMapper map = new ObjectMapper();
		try
		{
			return map.writeValueAsString(new ApiResponse(res));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return res;
	}

	@GET
	@Path("putroot/{treekey}/{key}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String putRoot(@PathParam("treekey") String treekey, @PathParam("value") String value, @PathParam("key") String key)
	{
		System.out.println("putroot Called");
		ObjectMapper map = new ObjectMapper();
		trees.addRoot(treekey, new EntryKeys(null, null, null, key), value);
		try
		{
			return map.writeValueAsString(new ApiResponse("Added"));
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
		System.out.println("search Called");
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
	@Path("get/{treekey}/{nodekey}")
	@Produces(MediaType.APPLICATION_JSON)
	public static String getElement(@PathParam("treekey") String tkey, @PathParam("nodekey") String nodekey)
	{
		System.out.println("get tree Called");
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
		System.out.println("inorder trans Called");
		ObjectMapper map = new ObjectMapper();
		PositionList<KeyValue<String>> list = trees.inOrderTraversal(key);
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
		KeyValue[] arry = new KeyValue[list.size()];
		Iterator<KeyValue<String>> listit = list.iterator();
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
		System.out.println("pre order trans Called");
		ObjectMapper map = new ObjectMapper();
		PositionList<KeyValue<String>> list = trees.PreOrderTraversal(key);
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
		KeyValue[] arry = new KeyValue[list.size()];
		Iterator<KeyValue<String>> listit = list.iterator();
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
		System.out.println("post order trans Called");
		ObjectMapper map = new ObjectMapper();
		PositionList<KeyValue<String>> list = trees.PostOrderTraversal(key);
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
		KeyValue[] arry = new KeyValue[list.size()];
		Iterator<KeyValue<String>> listit = list.iterator();
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

}
