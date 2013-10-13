/**
 * @(#)UniFind.java, Aug 27, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhongfeng
 *
 */
public class UnionFind<T> {
    private Map<T, Node> hash = new HashMap<T, Node>();
    
    public void add(T value) {
        Node node = new Node();
        hash.put(value, node);
    }
    
    public void union(T v1, T v2) {
        Node node1 = hash.get(v1);
        Node node2 = hash.get(v2);
        if (node1==null || node2==null) {
            throw new RuntimeException("invalid value");
        }
        Node root1 = find(node1);
        Node root2 = find(node2);
        if (root1 != root2) {
            if (root1.rank < root2.rank) {
                root1.parent = root2;
            } else {
                root2.parent = root1;
                if (root1.rank == root1.rank) {
                    root1.rank++;
                }
            }
        }
    }
    
    public boolean connected(T v1, T v2) {
        Node node1 = hash.get(v1);
        Node node2 = hash.get(v2);
        if (node1==null || node2==null) {
            throw new RuntimeException("invalid value");
        }
        return find(node1) == find(node2);
    }
    
    private Node find(Node node) {
        if (node.parent != node) {
            node.parent = find(node.parent);
        }
        return node.parent;
    }

    private class Node {
        private Node parent;
        private int rank;
        
        public Node() {
            this.rank = 1;
            this.parent = this;
        }
    }
}
