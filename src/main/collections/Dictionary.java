package main.collections;


import java.util.Iterator;

public class Dictionary<Key, Value> {

    private Key[] keys;
    private Value[] values;
    private int size;

    public Dictionary(){
        keys = (Key[]) new Object[8];
        values = (Value[]) new Object[8];
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    public void put(Key key, Value value){
        if (contains(key))
            return;

        if (size >= keys.length / 2)
            resize(keys.length  * 2);

        int index = hash(key);

        while (keys[index] != null)
            index = (index + 1) % keys.length;

        keys[index] = key;
        values[index] = value;

        size++;
    }

    public Value get(Key key){
        if (!contains(key))
            return null;

        int index = hash(key);

        while (keys[index] != key)
            index = (index + 1) % keys.length;

        return values[index];
    }

    public void delete(Key key){
        if (!contains(key))
            return;

        int index = hash(key);

        while (keys[index] != key)
            index = (index + 1) % keys.length;

        keys[index] = null;
        values[index] = null;
        size--;

        while (keys[index] != null){
            Key key_temp = keys[index];
            Value value_temp = values[index];

            keys[index] = null;
            values[index] = null;
            size--;

            put(key_temp, value_temp);

            index = (index + 1) % keys.length;
        }

        if (size <= keys.length / 4)
            resize(keys.length / 2);
    }

    public void update(Key key, Value value){
        if (!contains(key))
            throw new IndexOutOfBoundsException();

        int index = hash(key);

        while (keys[index] != key)
            index = (index + 1) % keys.length;

        values[index] = value;
    }

    public Dictionary<Value, Key> reversed(){
        Dictionary<Value, Key> reversed = new Dictionary<>();

        Iterator<Key> keys = keys();
        Iterator<Value> values = values();

        while (keys.hasNext())
            reversed.put(values.next(), keys.next());

        return reversed;
    }

    public boolean contains(Key key){
        for (int i = hash(key); keys[i] != null ; i = (i + 1) % keys.length)
            if (keys[i] == key)
                return true;

            return false;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    private void resize(int size){
        Key[] keys_temp = keys;
        Value[] values_temp = values;

        keys = (Key[]) new Object[size];
        values = (Value[]) new Object[size];

        for (int i = 0; i < keys_temp.length; i++)
            if (keys_temp[i] != null){
                put(keys_temp[i], values_temp[i]);

                this.size--;
            }
    }

    public Iterator<Key> keys(){
        return new Objects<Key>(keys);
    }

    public Iterator<Value> values(){
        return new Objects<Value>(values);
    }

    private class Objects<Item> implements Iterator<Item>{

        private Item[] temp;
        private int size;

        public Objects(Item[] items){
            temp = (Item[]) new Object[getSize()];

            int index = 0;

            for (int i = 0; i < items.length; i++)
                if (items[i] != null){
                    temp[index] = items[i];

                    index++;
                }
            size = index;
        }

        @Override
        public boolean hasNext() {
            return size != 0;
        }

        @Override
        public Item next() {
            Item result = temp[size - 1];

            size--;

            return result;
        }
    }
}
