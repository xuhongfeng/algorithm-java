/**
 * @(#)BSTSerialization.java, Oct 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

import me.cocodrum.algorithm.util.Utils;


/**
 * @author xuhongfeng
 *
 */
public class BSTSerialization {
    
    public int[] serialize(BST tree) {
        return BST.preorder(tree);
    }
    
    public BST deserialize(int[] a) {
        if (a==null || a.length==0) return null;
        
        Stream stream = new Stream(a);
        return deserialize(stream, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private BST deserialize(Stream stream, int min, int max) {
        if (!stream.hasNext()) return null;
        int v = stream.next();
        if (v<min || v>max) {
            stream.stepBack();
            return null;
        }
        
        BST root = new BST(v);
        root.left = deserialize(stream, min, v);
        root.right = deserialize(stream, v, max);
        
        return root;
    }
    
    private static class Stream {
        private int[] buf;
        private int index=0;
        
        public Stream(int[] a) {
            buf = a;
        }
        
        public void stepBack() {
            index--;
        }
        
        public boolean hasNext() {
            return index < buf.length;
        }
        
        public int next() {
            return buf[index++];
        }
    }
    
    private static class BST {
        private int value;
        private BST left;
        private BST right;
        
        public BST(int v) {
            this.value = v;
        }
        
        public static BST insert(BST root, int v) {
            if (root == null) {
                root = new BST(v);
                return root;
            }
            if (root.value == v) {
                return root;
            }
            if (v < root.value) {
                root.left = insert(root.left, v);
            } else {
                root.right = insert(root.right, v);
            }
            return root;
        }
        
        public static int[] preorder(BST root) {
            List<Integer> r = new LinkedList<Integer>();
            
            preorder(root, r);
            
            return Utils.listToArray(r);
        }
        
        private static void preorder(BST root, List<Integer> list) {
            if (root != null) {
                list.add(root.value);
                preorder(root.left, list);
                preorder(root.right, list);
            }
        }
        
        public static void main(String[] args) {
            BST tree = BST.insert(null, 1);
            
            BST.insert(tree, 4);
            BST.insert(tree, 9);
            BST.insert(tree, 0);
            BST.insert(tree, 6);
            BST.insert(tree, 14);
            BST.insert(tree, 43);
            BST.insert(tree, 8);
            BST.insert(tree, 3);
            BST.insert(tree, 4);
            
            int[] r = new BSTSerialization().serialize(tree);
            
            for (int i=0; i<r.length; i++) {
                System.out.print(r[i] + " ");
            }
            System.out.println();
            
            tree = new BSTSerialization().deserialize(r);
            r = new BSTSerialization().serialize(tree);
            
            for (int i=0; i<r.length; i++) {
                System.out.print(r[i] + " ");
            }
        }
    }
}
