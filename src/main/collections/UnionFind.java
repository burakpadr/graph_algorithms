package main.collections;

import java.util.Iterator;

public class UnionFind {

    private Dictionary<Integer, Integer> id;

    public UnionFind(){
        id = new Dictionary<>();
    }

    public int find(int object){
        if (!id.contains(object))
            return -1;

        while (id.get(object) != object)
            object = id.get(object);

        return object;
    }

    public boolean connected(int first_object, int second_object){
        if (!id.contains(first_object) || !id.contains(second_object))
            return false;

        return find(first_object) == find(second_object);
    }

    public void union(int first_object, int second_object){
        if (!id.contains(first_object))
            id.put(first_object, first_object);
        if (!id.contains(second_object))
            id.put(second_object, second_object);

        if (!connected(first_object, second_object))
            id.update(find(second_object), find(first_object));
    }

    public int getComponentSize(){
        int size = 0;

        List<Integer> marked = new List<>();

        Iterator<Integer> keys = id.keys();

        while (keys.hasNext()){
            int component = find(keys.next());

            if (!marked.contains(component)) {
                marked.add(component);

                size++;
            }
        }

        return size;
    }
}
