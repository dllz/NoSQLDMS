package db.data.structures.tree;

import db.data.structures.position.PositionList;
import db.models.tree.EntryKeys;
import db.models.tree.NodeKey;

import java.util.Iterator;

/**
 * Created by danie on 2017/04/25.
 */
public class TreeArray
{
    private PositionList<BinaryTree<Object>> list;
    private PositionList<BTNode> reverse;

    public TreeArray()
    {
        list	= new PositionList<>();
        reverse = new PositionList<>();
    }

    public synchronized void addLeft(String key, EntryKeys keys, Object value, Class type) {
        if(getTree(key) == null)
        {
            BTNode<Object> temp = new BTNode<>(null, null, null, new NodeKey(key, keys.getKey()), value, type);
            reverse.addLast(temp);
            BinaryTree<Object> tempTree = new BinaryTree<>(key, temp);

            list.addLast(tempTree);
        }
        else
        {
            Iterator<BinaryTree<Object>> treeIterator = list.iterator();
            BinaryTree<Object> tree = null;
            while(treeIterator.hasNext())
            {
                tree = treeIterator.next();
                if(tree.getKey().equals(key))
                {
                    BTNode parent = tree.searchNode(keys.getParentKey());
                    BTNode right = tree.searchNode(keys.getRightKey());
                    BTNode<Object> node = new BTNode<Object>(parent, null, right, new NodeKey(key, keys.getKey()), value, type);
                    reverse.addLast(node);
                    parent.setLeft(node);
                }
            }
        }
    }

    public synchronized void addRight(String key, EntryKeys keys, Object value, Class type) {
        if(getTree(key) == null)
        {
            BTNode<Object> temp = new BTNode<>(null, null, null, new NodeKey(key, keys.getKey()), value, type);
            reverse.addLast(temp);
            BinaryTree<Object> tempTree = new BinaryTree<>(key, temp);

            list.addLast(tempTree);
        }
        else
        {
            Iterator<BinaryTree<Object>> treeIterator = list.iterator();
            BinaryTree<Object> tree = null;
            while(treeIterator.hasNext())
            {
                tree = treeIterator.next();
                if(tree.getKey().equals(key))
                {
                    BTNode parent = tree.searchNode(keys.getParentKey());
                    BTNode left = tree.searchNode(keys.getLeftKey());
                    BTNode<Object> node = new BTNode<Object>(parent, left, null, new NodeKey(key, keys.getKey()), value, type);
                    reverse.addLast(node);
                    parent.setRight(node);
                }
            }
        }
    }

    public synchronized void addRoot(String key, EntryKeys keys, Object value, Class type) {
        if(getTree(key) == null)
        {
            BTNode<Object> temp = new BTNode<>(null, null, null, new NodeKey(key, keys.getKey()), value, type);
            reverse.addLast(temp);
            BinaryTree<Object> tempTree = new BinaryTree<>(key, temp);

            list.addLast(tempTree);
        }
        else
        {
            Iterator<BinaryTree<Object>> treeIterator = list.iterator();
            BinaryTree<Object> tree = null;
            while(treeIterator.hasNext())
            {
                tree = treeIterator.next();
                if(tree.getKey().equals(key))
                {
                    BTNode<Object> node = new BTNode<Object>(null, null, null, new NodeKey(key, keys.getKey()), value, type);
                    reverse.addLast(node);
                    tree.setRoot(node);
                }
            }
        }
    }

    public synchronized BinaryTree getTree(String key)
    {
        Iterator<BinaryTree<Object>> treeIterator = list.iterator();
        BinaryTree<Object> tree = null;
        while(treeIterator.hasNext())
        {
            tree = treeIterator.next();
            if(tree.getKey().equals(key))
            {
                return tree;
            }
            tree = null;
        }
        return null;
    }

    public synchronized PositionList<NodeKey> search (Object value)
    {
        PositionList<NodeKey> foundList = new PositionList<>();
        Iterator<BTNode> nodeIt = reverse.iterator();
        BTNode entry = null;
        while (nodeIt.hasNext())
        {
            entry = nodeIt.next();
            if(entry.element().equals(value))
            {
                foundList.addLast(entry.getKey());
            }
        }
        return foundList;
    }

    public synchronized BTNode<Object> getNode(NodeKey key)
    {
        BinaryTree<Object> tree = getTree(key.getTreeKey());
        if(tree != null)
        {
            return tree.searchNode(key.getNodeKey());
        }
        return null;
    }

    public synchronized Object getElement(NodeKey key)
    {
        BTNode<Object> node = getNode(key);
        if(node != null)
        {
            return node.element();
        }
        return null;
    }

    public synchronized PositionList<Object> inOrderTraversal(String key)
    {
        BinaryTree<Object> tree = getTree(key);
        if(tree != null)
        {
            PositionList<Object> elemts = new PositionList<>();
            tree.InorderElementTraversal(elemts, tree.root());
            return elemts;
        }
        return null;
    }

    public synchronized PositionList<Object> PostOrderTraversal(String key)
    {
        BinaryTree<Object> tree = getTree(key);
        if(tree != null)
        {
            PositionList<Object> elemts = new PositionList<>();
            tree.PostOrderElementTraversal(elemts, tree.root());
            return elemts;
        }
        return null;
    }

    public synchronized PositionList<Object> PreOrderTraversal(String key)
    {
        BinaryTree<Object> tree = getTree(key);
        if(tree != null)
        {
            PositionList<Object> elemts = new PositionList<>();
            tree.PreorderElementTraversal(elemts, tree.root());
            return elemts;
        }
        return null;
    }

}
