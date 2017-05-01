package db.data.structures.queue;//Overall PriorityQueue class: 5 marks ***********************************************

import db.data.structures.position.PositionList;

public class Queue
{
    private PositionList<String> list = null;

    /*
     * Default constructor
     */
    public Queue(){
        this.list = new PositionList<>();
    }

    /*
     * Insert method that store the entry in an unordered manner (you can add it at the end)
     */
    public synchronized void qeueu(String entry){
        list.addLast(entry);
    }

    /*
     * Remove the entry with the highest priority (typically 0 as an integer)
     * by performing a Heap sort and returning the root entry
     * 5 marks ***********************************************
     */
    public synchronized String dequeue(){
        return list.removeFirst();
    }

    /*
     * Returns the size of the Priority Queue
     */
    public synchronized Integer size(){
        return this.list.size();
    }

    /*
     * Returns whether the Priority Queue is empty or not.
     */
    public synchronized boolean isEmpty(){
        return this.list.isEmpty();
    }

    /*
     * The overridden toString method
     */
    public synchronized String toString(){
        return list.toString();
    }
}