/**
 * @(#)Graph.java, Sep 15, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.List;

/**
 * @author xuhongfeng
 *
 */
public interface Graph {
    public int[] getAllVertexes();
    public List<Edge> getAllEdges();
    public int[] getAdjacency(int vertex);
    public int size();
    public int weight(int i, int j);
    public void setWeight(int i, int j, int weight);
    public void setUndirectedWeight(int i, int j, int weight);
    public boolean linked(int i, int j);
    
    public static final class Edge {
        public final int start;
        public final int end;

        public Edge(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }
    }
}
