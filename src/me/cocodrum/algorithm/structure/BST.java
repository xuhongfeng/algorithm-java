/**
 * @(#)BFT.java, Aug 29, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author xuhongfeng
 *
 */
public class BST<K, V> implements Iterable<V> {
    private Node root;
    private final Comparator<K> cmp;
    
    public BST(Comparator<K> cmp) {
        super();
        this.cmp = cmp;
    }
    
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private Node current = BST.this.mim(root);
            
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public V next() {
                V value = current.value;
                current = successor(current);
                return value;
            }

            @Override
            public void remove() {
                Node toDelete = current;
                current = successor(current);
                delete(toDelete);
            }
        };
    }
    
    public V search(K key) {
        Node p = root;
        while(p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            if (cmp.compare(p.key, key) < 0) {
                p = p.right;
            } else{
                p = p.left;
            }
        }
        return null;
    }
    
    public void insert(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }
        Node p = root;
        while (true) {
            if (cmp.compare(key, p.key) <= 0) {
                if (p.left == null) {
                    p.left = new Node(key, value);
                    p.left.parent = p;
                    break;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    p.right = new Node(key, value);
                    p.right.parent = p;
                    break;
                } else {
                    p = p.right;
                }
            }
        }
    }
    
    public V mim() {
        Node node = mim(root);
        return node==null ? null : node.value;
    }
    
    private Node mim(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    public V max() {
        Node node = max(root);
        return node==null ? null : node.value;
    }
    
    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    private Node successor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return mim(node.right);
        }
        while (node.parent!=null
                && node.parent.right==node) {
            node = node.parent;
        }
        return node.parent;
    }
    
    private Node predecessor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return max(node.left);
        }
        while (node.parent!=null
                && node.parent.left==node) {
            node = node.parent;
        }
        return node;
    }
    
    private void delete(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null) {
            transparent(node.right, node);
        } else if (node.right == null) {
            transparent(node.left, node);
        } else {
            Node from = mim(node.right);
            if (from.parent != node) {
                transparent(from.right, from);
                from.right = node.right;
                from.right.parent = from;
            }
            transparent(from, node);
            from.left = node.left;
            from.left.parent = from;
        }
    }
    
    private void transparent(Node from, Node to) {
        if (to == null) {
            return;
        }
        if (to.parent == null) {
            root = from;
            return;
        }
        if (to == to.parent.left) {
            to.parent.left = from;
        } else {
            to.parent.right = from;
        }
        if (from != null) {
            from.parent = to.parent;
        }
        
        
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<V> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next()).append(",");
        }
        return sb.toString();
    }

    private class Node {
        private Node parent;
        private Node left;
        private Node right;
        private K key;
        private V value;
        
        public Node(K key, V value) {
            super();
            this.key = key;
            this.value = value;
        }
    }
    
    public static void main(String[] args) {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        BST<Integer, Integer> tree = new BST<Integer, Integer>(cmp);
        
        int[] a = new int[] {1, 3, 7, 2, 5, 9, 0, 8, 6, 4};
        for (int v:a) {
            tree.insert(v, v);
        }
        System.out.println(tree);
        
        Iterator<Integer> it = tree.iterator();
        it.next();
        it.next();
        it.remove();
        System.out.println(tree);
    }
}