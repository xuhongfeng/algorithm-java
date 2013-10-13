/**
 * @(#)AdjacencyList.java, Sep 15, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class AdjacencyList extends AbsGraph {
    private List<List<Node>> links;
    
    public AdjacencyList(int n) {
        super(n);
        links = new ArrayList<List<Node>>(n);
        for (int i=0; i<n; i++) {
            links.add(new LinkedList<Node>());
        }
    }

    @Override
    public int[] getAdjacency(int vertex) {
        List<Integer> list = new ArrayList<Integer>();
        for (Node node:links.get(vertex)) {
            list.add(node.vertex);
        }
        return Utils.listToArray(list);
    }

    @Override
    public int weight(int i, int j) {
        if (i == j) {
            return 0;
        }
        for (Node node:links.get(i)) {
            if (node.vertex == j) {
                return  node.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public void setWeight(int i, int j, int weight) {
        Node node = new Node(j, weight);
        links.get(i).add(node);
    }
    
    private class Node {
        private final int vertex;
        private final int weight;

        public Node(int vertex, int weight) {
            super();
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}