/**
 * @(#)Traverse.java, Oct 31, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xuhongfeng
 *
 */
public class Traverse {
    
    public void postOrder(Node tree) {
        if (tree == null) return;
        
        Node pre = null;
        List<Node> stack = new LinkedList<Node>();
        stack.add(tree);

        while (stack.size() > 0) {
            Node p = stack.remove(stack.size()-1);
            if (pre==null || pre.left==p || pre.right==p) {
                if (p.left!=null) {
                    stack.add(p);
                    stack.add(p.left);
                } else if (p.right != null) {
                    stack.add(p);
                    stack.add(p.right);
                } else {
                    visit(p);
                }
            } else if (p.left == pre) {
                if (p.right != null) {
                    stack.add(p);
                    stack.add(p.right);
                } else {
                    visit(p);
                }
            } else {
                visit(p);
            }
            pre = p;
        }
    }
    
    public void rPostOrder(Node tree) {
        if (tree == null) return;
        
        rPostOrder(tree.left);
        rPostOrder(tree.right);
        visit(tree);
    }
    
    public void inOrder(Node tree) {
        if (tree == null) return;
        
        List<Node> stack = new LinkedList<Node>();
        
        Node p = tree;
        while (p!=null) {
            stack.add(p);
            p = p.left;
        }
        
        while (stack.size() > 0) {
            p = stack.remove(stack.size()-1);
            visit(p);
            p = p.right;
            while (p!=null) {
                stack.add(p);
                p = p.left;
            }
        }
    }
    
    public void rInOrder(Node tree) {
        if (tree == null) return;
        rInOrder(tree.left);
        visit(tree);
        rInOrder(tree.right);
    }
    
    public void preOrder(Node tree) {
        if (tree == null) return;
        
        List<Node> stack = new LinkedList<Node>();
        stack.add(tree);
        while (stack.size() > 0) {
            Node p = stack.remove(stack.size()-1);
            visit(p);
            if (p.right != null) {
                stack.add(p.right);
            }
            if (p.left != null) {
                stack.add(p.left);
            }
        }
    }
    
    public void rPreorder(Node tree) {
        if (tree == null) return;
        visit(tree);
        rPreorder(tree.left);
        rPreorder(tree.right);
    }
    
    private void visit(Node node) {
        System.out.print(node.value + "  ");
    }

    private static class Node {
        private int value;
        Node left, right;
        
        public Node(int v) {
            this.value = v;
        }
    }
    
    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.left.right = new Node(3);
        tree.right = new Node(4);
        tree.right.right = new Node(5);
        tree.left.left = new Node(6);
        
        new Traverse().postOrder(tree);
        System.out.println();
        new Traverse().rPostOrder(tree);
    }
}
