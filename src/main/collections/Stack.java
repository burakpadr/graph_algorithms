package main.collections;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{

    private class Node{
        Item item;
        Node next;

        public Node(Item item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    private Node first;
    private int size;

    public void push(Item item){
        Node old_first = first;

        first = new Node(item, old_first);

        size++;
    }

    public Item pop(){
        Item result = first.item;

        first = first.next;

        size--;

        return result;
    }

    public boolean contains(Item item){
        for (Node x = first; x != null; x = x.next)
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
