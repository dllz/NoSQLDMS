package main.db.models.hash;

/**
 * Created by danie on 2017/04/10.
 */
public class EntryStorage<K>
{
    private K key;
    private String field;
    private Object value;

    public EntryStorage()
    {
    }

    public EntryStorage(K key, String field, Object value)
    {
        this.key = key;
        this.field = field;
        this.value = value;
    }

    public EntryStorage(K key, HashField hf)
    {
        this.key = key;
        this.field = hf.getField();
        this.value = hf.getValue();
    }

    public K getKey()
    {
        return key;
    }

    public void setKey(K key)
    {
        this.key = key;
    }

    public String getField()
    {
        return field;
    }

    public void setField(String field)
    {
        this.field = field;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }
}
