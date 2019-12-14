package main.collections;

import java.util.Iterator;

public class MinPQ<Item extends Comparable<Item>> implements Iterable<Item>{

    private Item[] pq;
    private int size;

    public MinPQ(){
        pq = (Item[]) new Comparable[2];
    }

    public void enqueue(Item item){
        size++;

        if (size >= pq.length / 2)
            resize(pq.length * 2);

        pq[size] = item;

        swim();
    }

    public Item popMin(){
        swap(1, size);

        Item result = pq[size];

        pq[size] = null;
        size--;

        if (size <= pq.length / 4)
            resize(pq.length / 2);

        sink();

        return result;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    private void swim(){
        int index = size;
        int compare_index = size / 2;

        while (compare_index > 0){
            if (pq[compare_index].compareTo(pq[index]) > 0){
                swap(index, compare_index);

                index = compare_index;
                compare_index /= 2;
            }
            else
                return;
        }
    }

    private void sink(){
        int index = 1;
        int compare_index = 2 * index;

        while (compare_index <= size){
            if (compare_index + 1 <= size)
                if (pq[compare_index].compareTo(pq[compare_index + 1]) > 0)
                    compare_index = compare_index + 1;

            if (pq[index].compareTo(pq[compare_index]) > 0){
                swap(index, compare_index);

                index = compare_index;
                compare_index *= 2;
            }
            else
                return;
        }
    }

    private void swap(int first_index, int second_index){
        Item temp = pq[first_index];

        pq[first_index] = pq[second_index];
        pq[second_index] = temp;
    }

    private void resize(int size){
        Item[] temp = pq;

        pq = (Item[]) new Comparable[size];

        for (int i = 1; i <= this.size; i++)
            pq[i] = temp[i];
    }

    public Iterator<Item> iterator(){
        return new Objects();
    }

    private class Objects implements Iterator<Item>{

        private MinPQ<Item> temp;

        public Objects(){
            temp = new MinPQ<Item>();

            for (int i = 1; i <= getSize(); i++)
                temp.enqueue(pq[i]);
        }

        @Override
        public boolean hasNext() {
            return !temp.isEmpty();
        }

        @Override
        public Item next() {
            return temp.popMin();
        }
    }
}
