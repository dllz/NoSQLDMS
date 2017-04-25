package db.models.tree;

/**
 * Created by danie on 2017/04/25.
 */
public class NodeKey
{
    private String treeKey;
    private String nodeKey;

    public String getTreeKey()
    {
        return treeKey;
    }

    public String getNodeKey()
    {
        return nodeKey;
    }

    public NodeKey(String treeKey, String nodeKey)
    {

        this.treeKey = treeKey;
        this.nodeKey = nodeKey;
    }
}
