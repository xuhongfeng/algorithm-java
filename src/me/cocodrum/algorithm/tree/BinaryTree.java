/**
 * @(#)BinaryTree.java, Sep 25, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

/**
 * @author xuhongfeng
 *
 */
public class BinaryTree<T> {
    public Node<T> root;
    
    public void insert(Node<T> parent, T value, boolean left) {
        if (parent == null) {
            setRoot(value);
            return;
        }
        Node<T> node = new Node<T>();
        node.value = value;
        
        if (left) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        node.parent = parent;
    }
    
    public void setRoot(T value) {
        Node<T> node = new Node<T>();
        node.value = value;
        this.root = node;
    }
    
    public static class Node<T> {
        public T value;
        public Node<T> parent;
        public Node<T> left;
        public Node<T> right;
    }
    
    public interface TraverseCalback<T> {
        public void visit(Node<T> node);
    }
}
