/**
 * @(#)BinaryTreeSerialize.java, Oct 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

/**
 * @author xuhongfeng
 *
 */
public class BinaryTreeSerialize {

    public static String serialize(Node tree) {
        StringBuilder sb = new StringBuilder();
        preorder(tree, sb);
        return sb.toString();
    }
    
    private static void preorder(Node tree, StringBuilder sb) {
        if (tree == null) {
            sb.append("#");
            return;
        }
        sb.append(tree.value);
        preorder(tree.left, sb);
        preorder(tree.right, sb);
    }
    
    public static Node deserialize(String s) {
        if (s==null) return null;
        Stream stream = new Stream(s.toCharArray());
        return deserizlize(stream);
    }
    
    private static Node deserizlize(Stream stream) {
        if (!stream.hasNext()) return null;
        char c = stream.next();
        if (c == '#') {
            return null;
        }
        Node root = new Node(Integer.valueOf(String.valueOf(c)));
        root.left = deserizlize(stream);
        root.right = deserizlize(stream);
        return root;
    }
    
    private static class Stream {
        private char[] chars;
        private int index;
        
        public Stream(char[] c) {
            chars = c;
            index = 0;
        }
        
        public boolean hasNext() {
            return index < chars.length;
        }
        
        public char next() {
            return chars[index++];
        }
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
        Node tree = new Node(7);
        tree.left = new Node(5);
        tree.right = new Node(1);
        tree.left.right = new Node(8);
        tree.left.right.left = new Node(4);
        
        String s = serialize(tree);
        System.out.println(s);
        tree = deserialize(s);
        s = serialize(tree);
        System.out.println(s);
    }
}
