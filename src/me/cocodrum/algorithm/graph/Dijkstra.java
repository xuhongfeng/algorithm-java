/**
 * @(#)Dijkstra.java, Sep 15, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.cocodrum.algorithm.data.IntPair;
import me.cocodrum.algorithm.structure.Heap;

/**
 * @author xuhongfeng
 *
 */
public class Dijkstra {
    
    public Result solve(Graph g, int s) {
        DataHolder data = new DataHolder();
        data.init(g, s);
        
        while (!data.heap.isEmpty()) {
            Item item = data.heap.extractTop();
            
            int v = item.vertex;
            data.result.put(v, item);
            
            for (int a:g.getAdjacency(v)) {
                if (!data.result.containsKey(a) && relax(data, v, a)) {
                    data.heap.update(data.get(a));
                    data.get(a).prev = v;
                }
            }
        }
        
        return data.exportResult();
    }
    
    private boolean relax(DataHolder data, int i, int j) {
        int newDistance = data.get(i).distance + data.g.weight(i, j);
        if (newDistance < data.get(j).distance) {
            data.get(j).distance = newDistance;
            return true;
        }
        return false;
    }
    
    private class DataHolder {
        private Heap<Item> heap = new Heap<Dijkstra.Item>(CMP);
        private Map<Integer, Item> hash = new HashMap<Integer, Item>();
        private int s;
        private Graph g;
        private Map<Integer, Item> result = new HashMap<Integer, Item>();
        
        public Item get(int vertex) {
            return hash.get(vertex);
        }
        
        public void init(Graph g, int s) {
            this.g = g;
            this.s = s;
            
            for (int v:g.getAllVertexes()) {
                Item item = new Item(v);
                if (v == s) {
                    item.distance = 0;
                }
                heap.insert(item);
                hash.put(v, item);
            }
        }
        
        public Result exportResult() {
            Result r = new Result();
            for (Map.Entry<Integer, Item> entry:result.entrySet()) {
                int vertex = entry.getKey();
                int distance = entry.getValue().distance;
                IntPair pair = new IntPair(vertex, distance);
                r.pairs.add(pair);
            }
            return r;
        }
    }
    
    private Comparator<Item> CMP = new Comparator<Dijkstra.Item>() {

        @Override
        public int compare(Item o1, Item o2) {
            if (o1.distance < o2.distance) {
                return -1;
            }
            if (o1.distance > o2.distance) {
                return 1;
            }
            return 0;
        }
    };
    
    private class Item {
        private final int vertex;
        private int distance = Integer.MAX_VALUE;
        private int prev = -1;
        
        public Item(int vertex) {
            super();
            this.vertex = vertex;
        }

        @Override
        public String toString() {
            return "Item [vertex=" + vertex + ", distance=" + distance
                    + ", prev=" + prev + "]";
        }
    }

    private class Result {
        private List<IntPair> pairs = new LinkedList<IntPair>();

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (IntPair p:pairs) {
                sb.append(p.getFirst() + "  :  " + p.getSecond() + "\n");
            }
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        Graph g = new AdjacencyList(6);
        g.setUndirectedWeight(0, 1, 7);
        g.setUndirectedWeight(0, 2, 9);
        g.setUndirectedWeight(0, 5, 14);
        g.setUndirectedWeight(1, 3, 15);
        g.setUndirectedWeight(1, 2, 10);
        g.setUndirectedWeight(2, 3, 11);
        g.setUndirectedWeight(2, 5, 2);
        g.setUndirectedWeight(3, 4, 6);
        g.setUndirectedWeight(4, 5, 9);
        
        Result r = new Dijkstra().solve(g, 0);
        System.out.println(r);
    }
}
