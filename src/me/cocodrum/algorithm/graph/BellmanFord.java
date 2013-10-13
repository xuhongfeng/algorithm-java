/**
 * @(#)BellmanFord.java, Sep 16, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.cocodrum.algorithm.data.IntPair;
import me.cocodrum.algorithm.graph.Graph.Edge;

/**
 * @author xuhongfeng
 *
 */
public class BellmanFord {

    public Result solve(Graph g, int s) {
        Map<Integer, Item> hash = new HashMap<Integer, Item>();
        for (int v:g.getAllVertexes()) {
            Item item = new Item(v);
            if (v == s) {
                item.distance = 0;
            }
            hash.put(v, item);
        }
        
        List<Edge> edges = g.getAllEdges();
        for (int i=0; i<g.size()-1; i++) {
            for (Edge edge:edges) {
                relax(g, hash.get(edge.start), hash.get(edge.end));
            }
        }
        
        Result r = new Result();
        for (Map.Entry<Integer, Item> entry:hash.entrySet()) {
            int vertex = entry.getKey();
            int distance = entry.getValue().distance;
            IntPair p = new IntPair(vertex, distance);
            r.pairs.add(p);
        }
        
        return r;
    }
    
    private boolean relax(Graph g, Item a, Item b) {
        int newDistance = a.distance + g.weight(a.vertex, b.vertex);
        if (newDistance < b.distance) {
            b.distance = newDistance;
            return true;
        }
        return false;
    }
    
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
        
        Result r = new BellmanFord().solve(g, 0);
        System.out.println(r);
    }
}