/**
 * @(#)Tree.java, Oct 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

/**
 * @author xuhongfeng
 *
 */
public class Tree {

    private int value;
    private Tree child;
    private Tree sibling;
    
    public Tree(int v) {
        this.value = v;
    }
    
    public static String serialize(Tree tree) {
        StringBuilder sb = new StringBuilder();
        serialize(tree, sb);
        return sb.toString();
    }
    
    private static void serialize(Tree tree, StringBuilder sb) {
        if (tree == null) {
            sb.append("#");
            return;
        }
        sb.append(tree.value);
        serialize(tree.child, sb);
        serialize(tree.sibling, sb);
    }
    
    public static Tree deserialize(String s) {
        return deserialize(new Stream(s));
    }
    
    private static Tree deserialize(Stream stream) {
        if (!stream.hasNext()) {
            return null;
        }
        char c = stream.next();
        if (c == '#') {
            return null;
        }
        Tree root = new Tree(Integer.valueOf(String.valueOf(c)));
        root.child = deserialize(stream);
        root.sibling = deserialize(stream);
        return root;
    }
    
    private static class Stream {
        private final char[] chars;
        private int index;
        
        public Stream(String s) {
            chars = s.toCharArray();
            index = 0;
        }
        
        public boolean hasNext() {
            return index < chars.length;
        }
        
        public char next() {
            return chars[index++];
        }
    }
    
    public static void main(String[] args) {
        Tree tree = new Tree(1);
        tree.child = new Tree(2);
        tree.child.sibling = new Tree(3);
        tree.child.sibling.child = new Tree(4);
        tree.child.sibling.sibling = new Tree(5);
        
        String s = serialize(tree);
        System.out.println(s);
        tree = deserialize(s);
        serialize(tree);
        System.out.println(s);
    }
}
