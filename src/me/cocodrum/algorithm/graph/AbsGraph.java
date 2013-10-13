/**
 * @(#)AbsGraph.java, Sep 15, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xuhongfeng
 *
 */
public abstract class AbsGraph implements Graph {
    protected final int n;
    protected final int[] vertexes;
    
    @Override
    public boolean linked(int i, int j) {
        return weight(i, j) != Integer.MAX_VALUE;
    }

    public AbsGraph(int n) {
        super();
        this.n = n;
        vertexes = new int[n];
        for (int i=0; i<n; i++) {
            vertexes[i] = i;
        }
    }
    
    @Override
    public int[] getAllVertexes() {
        return vertexes;
    }
    
    @Override
    public List<Edge> getAllEdges() {
        List<Edge> edges = new LinkedList<Edge>();
        for (int start:getAllVertexes()) {
            for (int end:getAdjacency(start)) {
                edges.add(new Edge(start, end));
            }
        }
        return edges;
    }
    
    @Override
    public void setUndirectedWeight(int i, int j, int weight) {
        setWeight(i, j, weight);
        setWeight(j, i, weight);
    }

    @Override
    public int size() {
        return n;
    }
}
