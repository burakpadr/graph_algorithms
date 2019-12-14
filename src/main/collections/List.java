package main.collections;

import java.util.Iterator;

public class List<Item> implements Iterable<Item>{

    private Item[] list;
    private int size;

    public List(){
        list = (Item[]) new Object[8];
    }

    private int hash(Item item){
        return (item.hashCode() & 0x7fffffff) % list.length;
    }

    public void add(Item item){
        if (size >= list.length / 2)
            resize(list.length * 2);

        int index = hash(item);

        while (list[index] != null)
            index = (index + 1) % list.length;

        list[index] = item;

        size++;
    }

    public void delete(Item item){
        if (!contains(item))
            return;

        int index = hash(item);

        while (list[index] != item)
            index = (index + 1) % list.length;

        list[index] = null;
        size--;

        index = (index + 1) % list.length;

        while (list[index] != null){
            Item temp = list[index];

            list[index] = null;
            size--;

            add(temp);

            index = (index + 1) % list.length;
        }

        if (size <= list.length / 4)
            resize(list.length / 2);
    }

    public Item getItem(int index){
        return list[index];
    }

    public int getIndex(Item item){
        if (!contains(item))
            return -1;

        int index = hash(item);

        while (list[index] != item)
            index = (index + 1) % list.length;

        return index;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    public boolean contains(Item item){
        for (int i = hash(item); list[i] != null; i = (i + 1) % list.length)
            if (list[i] == item)
                return true;

        return false;
    }

    private void resize(int size){
        Item[] temp = list;

        list = (Item[]) new Object[size];

        for (int i = 0; i < temp.length; i++)
            if (temp[i] != null){
                add(temp[i]);

                this.size--;
            }
    }

    public Iterator<Item> iterator(){
        return new Objects();
    }

    private class Objects implements Iterator<Item>{

        Item[] items;
        int size = 0;

        public Objects(){
            items = (Item[]) new Object[getSize()];
            int index = 0;

            for (int i = 0; i < list.length; i++) {
                if (list[i] != null){
                    items[index] = list[i];

                    index++;
                }
            }

            size = index;
        }

        @Override
        public boolean hasNext() {
            return size != 0;
        }

        @Override
        public Item next() {
            Item result = items[size - 1];

            size--;

            return result;
        }
    }
}
