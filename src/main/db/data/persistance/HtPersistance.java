package main.db.data.persistance;

import main.db.data.structures.hash.HashTable;

import java.io.*;

/**
 * Created by danie on 2017/04/22.
 */
public class HtPersistance
{
    public static void saveht(HashTable ht)
    {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try
        {
            fout = new FileOutputStream("data/ht.dat");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(ht);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static HashTable readht()
    {
        FileInputStream streamIn = null;
        ObjectInputStream objectinputstream = null;
        HashTable<String> temp = new HashTable<String>();
        try {
            streamIn = new FileInputStream("data/ht.dat");
            objectinputstream = new ObjectInputStream(streamIn);
            HashTable ht = null;
            ht = (HashTable) objectinputstream.readObject();
            if(ht != null)
                return ht;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectinputstream != null){
                try
                {
                    objectinputstream .close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return temp;
    }
}
