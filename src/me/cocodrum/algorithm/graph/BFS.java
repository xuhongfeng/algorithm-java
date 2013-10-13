/**
 * @(#)BFS.java, Sep 15, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.cocodrum.algorithm.structure.LinkedQueue;
import me.cocodrum.algorithm.structure.Queue;

/**
 * @author xuhongfeng
 *
 */
public class BFS {
    private GraphColor colors;

    public Result solve(Graph g) {
        colors = new GraphColor();
        colors.build(g);
        
        for (int v:g.getAllVertexes()) {
            if (colors.isWhite(v)) {
                explore(g, v);
            }
        }
        
        return colors.exportResult();
    }
        
    private void explore(Graph g, int s) {
        Queue<Integer> queue = new LinkedQueue<Integer>();
        colors.visit(s);
        colors.setGray(s);
        colors.get(s).distance = 0;
        queue.enQueue(s);
        while (!queue.isEmpty()) {
            int v = queue.deQueue();
            int[] adj = g.getAdjacency(v);
            for (int a:adj) {
                if (colors.isWhite(a)) {
                    colors.visit(a);
                    colors.get(a).prev = v;
                    colors.get(a).distance = colors.get(v).distance + g.weight(v, a);
                    colors.setGray(a);
                    queue.enQueue(a);
                }
            }
            colors.setBlack(v);
        }
    }
    
    private enum Color {
        WHITE, GRAY, BLACK;
    }
    
    private class GraphColorItem {
        private Color color = Color.WHITE;
        private int distance = Integer.MAX_VALUE;
        private int prev= -1;
    }
    
    private class GraphColor {
        private Map<Integer, GraphColorItem> items = new HashMap<Integer, GraphColorItem>();
        private int time;
        private int[] result;
        
        public void build(Graph g) {
            for (int vertex:g.getAllVertexes()) {
                GraphColorItem item = new GraphColorItem();
                items.put(vertex, item);
            }
            result = new int[g.size()];
            time = 0;
        }
        
        public void visit(int vertex) {
            result[time++] = vertex;
        }
        
        public GraphColorItem get(int vertex) {
            return items.get(vertex);
        }
        
        public void setBlack(int vertex) {
            setColor(vertex, Color.BLACK);
        }
        
        private void setGray(int vertex) {
            setColor(vertex, Color.GRAY);
        }
        
        public boolean isWhite(int vertex) {
            return isColor(vertex, Color.WHITE);
        }
        
        private boolean isColor(int vertex, Color color) {
            return items.get(vertex).color == color;
        }
        
        private void setColor(int vertex, Color color) {
            items.get(vertex).color = color;
        }
        
        public Result exportResult() {
            return new Result(this.result);
        }
    }
    
    private class Result {
        private final int[] vertexes;

        public Result(int[] vertexes) {
            super();
            this.vertexes = vertexes;
        }

        @Override
        public String toString() {
            return "Result [vertexes=" + Arrays.toString(vertexes) + "]";
        }
    }
    
    
    public static void main(String[] args) {
        Graph g = new AdjacencyList(12);
//        Graph g = new AdjacencyMatrix(12);
        g.setWeight(0, 1, 1);
        g.setWeight(0, 2, 1);
        g.setWeight(0, 3, 1);
        g.setWeight(1, 4, 1);
        g.setWeight(1, 5, 1);
        g.setWeight(4, 8, 1);
        g.setWeight(4, 9, 1);
        g.setWeight(3, 6, 1);
        g.setWeight(3, 7, 1);
        g.setWeight(6, 10, 1);
        g.setWeight(6, 11, 1);
        
        System.out.print(new BFS().solve(g));
    }
}
