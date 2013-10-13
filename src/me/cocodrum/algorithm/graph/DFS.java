/**
 * @(#)DFS.java, Sep 15, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author xuhongfeng
 *
 */
public class DFS {
    private GraphColor colors;
    
    public Result solve(Graph g, int s) {
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
//        _recursive(g, s);
        _interactive(g, s);
    }
    
    private void _interactive(Graph g, int s) {
        Stack<Integer> stack = new Stack<Integer>();
        
        preVisit(s);
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            boolean found = false;
            for (int a:g.getAdjacency(v)) {
                if (colors.isWhite(a)) {
                    preVisit(a);
                    found = true;
                    stack.push(v);
                    stack.push(a);
                    break;
                }
            }
            if (!found) {
                postVisit(v);
            }
        }
    }
    
    private void preVisit(int s) {
        colors.visit(s);
        colors.get(s).start = colors.time++; 
        colors.setGray(s);
    }
    
    private void postVisit(int s) {
        colors.setBlack(s);
        colors.get(s).end = colors.time++; 
    }
    
    private void _recursive(Graph g, int s) {
        preVisit(s);
        for (int v:g.getAdjacency(s)) {
            if (colors.isWhite(v)) {
                _recursive(g, v);
            }
        }
        postVisit(s);
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
    
    private enum Color {
        WHITE, GRAY, BLACK;
    }
    
    private class GraphColorItem {
        private Color color = Color.WHITE;
        private int prev= -1;
        private int start;
        private int end;
    }
    
    private class GraphColor {
        private Map<Integer, GraphColorItem> items = new HashMap<Integer, GraphColorItem>();
        private int time;
        private int[] result;
        private int idx = 0;
        
        public void build(Graph g) {
            for (int vertex:g.getAllVertexes()) {
                GraphColorItem item = new GraphColorItem();
                items.put(vertex, item);
            }
            result = new int[g.size()];
            time = 0;
        }
        
        public void visit(int vertex) {
            result[idx++] = vertex;
        }
        
        public GraphColorItem get(int vertex) {
            return items.get(vertex);
        }
        
        public void setBlack(int vertex) {
            setColor(vertex, Color.BLACK);
        }
        
        public void setGray(int vertex) {
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
    
    public static void main(String[] args) {
        Graph g = new AdjacencyList(12);
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
        
        System.out.print(new DFS().solve(g, 0));
    }
}