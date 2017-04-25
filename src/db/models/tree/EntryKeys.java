package db.models.tree;

/**
 * Created by danie on 2017/04/25.
 */
public class EntryKeys
{
    private String parentKey;
    private String leftKey;
    private String rightKey;
    private String key;

    public String getParentKey()
    {
        return parentKey;
    }

    public String getLeftKey()
    {
        return leftKey;
    }

    public String getRightKey()
    {
        return rightKey;
    }

    public String getKey()
    {
        return key;
    }

    public EntryKeys(String parentKey, String leftKey, String rightKey, String key)
    {

        this.parentKey = parentKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.key = key;
    }
}
