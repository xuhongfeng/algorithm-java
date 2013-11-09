/**
 * @(#)IsBST.java, Oct 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

/**
 * @author xuhongfeng
 *
 */
public class IsBST {

    public boolean isBst(Node tree) {
        return isBst(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean isBst(Node tree, int min, int max) {
        if (tree == null) return true;
        int v = tree.value;
        if (v<min || v>max) return false;
        return isBst(tree.left, min, v) && isBst(tree.right, v, max);
    }
    
    private static class Node {
        private int value;
        private Node left;
        private Node right;
        
        public Node(int v) {
            value = v;
        }
    }
    
    public static void main(String[] args) {
        Node tree = new Node(10);
        tree.left = new Node(5);
        tree.right = new Node(15);
        tree.left.right = new Node(8);
        tree.left.right.left = new Node(4);
        
        System.out.println(new IsBST().isBst(tree));
    }
}
