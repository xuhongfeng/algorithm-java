/**
 * @(#)BST2List.java, Oct 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

/**
 * @author xuhongfeng
 *
 */
public class BST2List {
    
    public static Node toList(Node tree) {
        if (tree == null) return null;
        return _toList(tree).first;
    }
    
    private static Pair _toList(Node tree) {
        if (tree == null) return null;
        
        Node head = tree;
        Node tail = tree;
        
        if (tree.left != null) {
            Pair p = _toList(tree.left);
            p.second.right = tree;
            tree.left = p.second;
            head = p.first;
        }
        if (tree.right != null) {
            Pair p = _toList(tree.right);
            tree.right = p.first;
            p.first.left = tree;
            tail = p.second;
        }
        head.left = null;
        tail.right = null; 
        
        return new Pair(head, tail);
    }
    
    public static Node toTree(Node list) {
        if (list == null) return null;
        
        Node p = list;
        int count = 0;
        while (p != null) {
            count++;
            p = p.right;
        }
        
        return toTree(list, count).first;
    }
    
    private static Pair toTree(Node list, int count) {
        if (count == 0) return null;
        if (count == 1) {
            Node next = list.right;
            list.left = list.right = null;
            return new Pair(list, next);
        }
        int l = count/2;
        Pair left = toTree(list, l);
        Node root = left.second;
        root.left = left.first;
        
        int r = count - l - 1;
        if (r > 0) {
            Pair right = toTree(root.right, r);
            root.right = right.first;
            return new Pair(root, right.second);
        } else {
            Node next = root.right;
            root.right = null;
            return new Pair(root, next);
        }
        
    }
    
    private static class Pair {
        private final Node first;
        private final Node second;
        
        public Pair(Node first, Node second) {
            super();
            this.first = first;
            this.second = second;
        }
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
        
        public Node(int v) {
            this.value = v;
        }
        
        public void insert(int v) {
            insert(this, v);
        }
        
        public static Node insert(Node root, int v) {
            if (root == null) return new Node(v);
            
            if (root.value == v) return root;
            
            if (v < root.value) {
                root.left = insert(root.left, v);
            } else {
                root.right = insert(root.right, v);
            }
            return root;
        }
    }
    
    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.insert(9);
        tree.insert(3);
        tree.insert(8);
        tree.insert(6);
        tree.insert(2);
        
        Node list = toList(tree);
        
        Node p = list;
        while (p != null) {
            System.out.print(p.value + "  ");
            p = p.right;
        }
        System.out.println();
        
        tree = toTree(list);
        list = toList(tree);
        p = list;
        while (p != null) {
            System.out.print(p.value + "  ");
            p = p.right;
        }
    }
}
