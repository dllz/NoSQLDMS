package db.data.persistance;

import db.data.structures.tree.BinaryTree;
import db.data.structures.tree.TreeArray;

import java.io.*;

/**
 * Created by danie on 2017/04/25.
 */
public class TreePersistance
{
    public static void save(BinaryTree object)
    {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try
        {
            fout = new FileOutputStream("data/tree.dat");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static TreeArray read()
    {
        FileInputStream streamIn = null;
        ObjectInputStream objectinputstream = null;
        TreeArray temp = new TreeArray();
        try {
            streamIn = new FileInputStream("data/tree.dat");
            objectinputstream = new ObjectInputStream(streamIn);
            TreeArray object = null;
            object = (TreeArray) objectinputstream.readObject();
            if(object != null)
                return object;

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
