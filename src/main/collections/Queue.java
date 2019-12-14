package main.collections;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{

    private class Node{
        Item item;
        Node next;

        public Node(Item item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public void enqueue(Item item){
        Node old_last = last;

        last = new Node(item, null);

        if (first == null)
            first = last;
        else
            old_last.next = last;

        size++;
    }

    public Item dequeue(){
        Item result = first.item;

        first = first.next;

        size--;

        return result;
    }

    public boolean contains(Item item){
        for (Node x = first;  x != null; x = x.next)
            if (x.item.equals(item))
                return true;

        return false;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    public Iterator<Item> iterator(){
        return new Objects();
    }

    private class Objects implements Iterator<Item>{

        private Node temp = first;

        @Override
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public Item next() {
            Item result = temp.item;

            temp = temp.next;

            return result;
        }
    }
}
