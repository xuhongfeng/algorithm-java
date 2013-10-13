/**
 * @(#)MaxConsecutive.java, Sep 5, 2013.
 */
package me.cocodrum.algorithm.careercup;

import java.util.HashMap;
import java.util.Map;

import me.cocodrum.algorithm.data.IntPair;

/**
 * http://www.careercup.com/question?id=19778663
 * 
 * @author xuhongfeng
 */
public class MaxConsecutive {

    public IntPair resolve(int[] input) {
        if (input == null || input.length == 0) {
            return null;
        }
        UF uf = new UF();
        for (int a: input) {
            uf.insert(a);
        }
        return uf.getMax();
    }

    private class UF {
        private Map<Integer, Node> hash = new HashMap<Integer, Node>();

        private Node max;

        public void insert(int value) {
            if (hash.containsKey(value)) {
                return;
            }
            Node node = new Node(value);
            hash.put(value, node);
            if (max == null) {
                max = node;
            }

            int left = value - 1, right = value + 1;
            if (hash.containsKey(left)) {
                union(left, value);
            }
            if (hash.containsKey(right)) {
                union(value, right);
            }
        }

        public IntPair getMax() {
            if (max == null) {
                return null;
            }
            return new IntPair(max.start, max.end);
        }

        private Node find(Node node) {
            if (node.parent == node) {
                return node;
            }
            node.parent = find(node.parent);
            return node.parent;
        }

        private void union(int v1, int v2) {
            Node node1 = hash.get(v1);
            Node node2 = hash.get(v2);
            Node p1 = find(node1);
            Node p2 = find(node2);
            Node newParent = null;
            if (p1.rank > p2.rank) {
                newParent = p1;
                p2.parent = p1;
            } else {
                newParent = p2;
                p1.parent = p2;
                if (p1.rank == p2.rank) {
                    newParent.rank++;
                }
            }
            if (p1.end == p2.start - 1) {
                newParent.start = p1.start;
                newParent.end = p2.end;
            } else {
                newParent.start = p2.start;
                newParent.end = p1.end;
            }
            if (newParent.end - newParent.start > max.end - max.start) {
                max = newParent;
            }
        }

    }

    private class Node {
        private Node parent;

        private int start;

        private int end;

        private int rank;

        public Node(int value) {
            start = end = value;
            parent = this;
            rank = 1;
        }
    }
    
    public static void main(String[] args) {
        int[] input = new int[]{ 5, 1, 9, 3, 8, 20, 4, 10, 2, 11, 3};
        IntPair pair = new MaxConsecutive().resolve(input);
        System.out.println(pair);
    }
}
