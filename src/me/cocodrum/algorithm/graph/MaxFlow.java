/**
 * @(#)MaxFlow.java, Sep 21, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.cocodrum.algorithm.structure.Heap;
import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class MaxFlow {
    private Graph g;
    private Graph r;
    private int src;
    private int sink;
    
    public int resolve(Graph g, int src, int sink) {
        this.g = g;
        this.src = src;
        this.sink = sink;
        this.r = new Graph(g);
        
        while(true) {
            Path path = getAugmentingPath();
            if (path == null) {
                break;
            }
            applyAugmentingPath(path);
        }
        int flow = 0;
        for (int a:r.getAdjacency(sink)) {
            flow += r.weight(sink, a);
        }
        return flow;
    }
    
    private void applyAugmentingPath(Path path) {
        for (int i=0; i<path.vertexes.length-1; i++) {
            int s =path.vertexes[i];
            int t =path.vertexes[i+1];
            r.setWeight(s, t, r.weight(s, t) - path.capacity);
            r.setWeight(t, s, r.weight(t, s) + path.capacity);
        }
    }
    
    private Path getAugmentingPath() {
        //priority queue
        Heap<QueueNode> queue = new Heap<QueueNode>(CMP);
        QueueNode source = new QueueNode(src, -1, Integer.MAX_VALUE);
        queue.insert(source);
        
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(src);
        
        Map<Integer, QueueNode> hash = new HashMap<Integer, QueueNode>();
        hash.put(src, source);
        
        while (!queue.isEmpty()) {
            QueueNode node = queue.extractTop();
            int v = node.vertex;
            boolean foundSink = false;
            for (int a:r.getAdjacency(v)) {
                if (visited.contains(a)) {
                    continue;
                }
                int capacity = r.weight(v, a);
                if (node.capacity < capacity) {
                    capacity = node.capacity;
                }
                QueueNode t = new QueueNode(a, v, capacity);
                queue.insert(t);
                visited.add(a);
                hash.put(a, t);
                if (a == sink) {
                    foundSink = true;
                    break;
                }
            }
            if (foundSink) {
                break;
            }
        }
        QueueNode sinkNode = hash.get(sink);
        if (sinkNode == null) {
            return null;
        }
        int pathCapacity = sinkNode.capacity;
        List<Integer> pathList = new LinkedList<Integer>();
        pathList.add(sink);
        QueueNode t = sinkNode;
        while (t.prev != -1) {
            pathList.add(0, t.prev);
            t = hash.get(t.prev);
        }
        int[] pathArray = Utils.listToArray(pathList);
        
        return new Path(pathArray, pathCapacity);
    }
    
    private final Comparator<QueueNode> CMP = new Comparator<MaxFlow.QueueNode>() {
        @Override
        public int compare(QueueNode o1, QueueNode o2) {
            if (o1.capacity > o2.capacity) {
                return -1;
            } else if (o1.capacity < o2.capacity) {
                return 1;
            }
            return 0;
        }
    };
    
    private class QueueNode {
        private int vertex;
        private int prev;
        private int capacity;
        
        public QueueNode(int vertex, int prev, int capacity) {
            super();
            this.vertex = vertex;
            this.prev = prev;
            this.capacity = capacity;
        }
    }
    
    private class Path {
        private int[] vertexes;
        private int capacity;
        
        public Path(int[] vertexes, int capacity) {
            super();
            this.vertexes = vertexes;
            this.capacity = capacity;
        }
    }

    private static class Graph extends AdjacencyMatrix {

        public Graph(int n) {
            super(n, 0);
        }
        
        public Graph(Graph g) {
            this(g.n);
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    matrix[i][j] = g.matrix[i][j];
                }
            }
        }
        
        @Override
        public boolean linked(int i, int j) {
            return weight(i, j) != 0;
        }
    }
    
    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.setWeight(0, 1, 3);
        g.setWeight(0, 2, 1);
        g.setWeight(1, 3, 3);
        g.setWeight(2, 3, 5);
        g.setWeight(2, 4, 4);
        g.setWeight(3, 6, 2);
        g.setWeight(4, 5, 2);
        g.setWeight(5, 6, 3);
        
        System.out.println(new MaxFlow().resolve(g, 0, 6));
    }
}
