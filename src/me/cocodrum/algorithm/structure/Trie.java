/**
 * @(#)Trie.java, Aug 13, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author xuhongfeng
 *
 */
public class Trie {
    private final Node root = new Node();
    
    public static Trie build(String[] input) {
        Trie t = new Trie();
        for (String s:input) {
            t.insert(s);
        }
        return t;
    }
    
    public Node insert(String word) {
        if (word==null || word.length()==0) {
            return null;
        }
        Node p = this.root;
        for (int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            Node node = p.getChild(c);
            if (node == null) {
                node = new Node();
                node.prefix = word.substring(0, i+1);
                node.parent = p;
                p.childs.put(c, node);
            }
            p = node;
        }
        p.hit();
        return p;
    }
    
    public List<Node> traverse(Node node) {
        List<Node> ret = new LinkedList<Node>();
        if (node == null) {
            return ret;
        }
        
        if (node.count > 0) {
            ret.add(node);
        }
        Queue<Node> queue = new LinkedQueue<Node>();
        queue.enQueue(node);
        while (!queue.isEmpty()) {
            Node p = queue.deQueue();
            for (Node c:p.childs.values()) {
                if (c.count > 0) {
                    ret.add(c);
                }
                if (c.childs.size() > 0) {
                    queue.enQueue(c);
                }
            }
        }
        return ret;
    }
    
    public Node find(String query) {
        if (query == null) {
            return null;
        }
        Node p = this.root;
        for (char c:query.toCharArray()) {
            Node node = p.getChild(c);
            if (node == null) {
                return null;
            }
            p = node;
        }
        return p;
    }
    
    public List<Node> findByPrefix(String query) {
        Node node = find(query);
        return traverse(node);
    }
    
    public List<String> findByPrefixAsString(String query) {
        List<Node> nodes = findByPrefix(query);
        List<String> ret = new LinkedList<String>();
        for (Node node:nodes) {
            ret.add(node.prefix);
        }
        return ret;
    }
     
    private static class Node {
        private Node parent;
        private String prefix = "";
        private int count = 0;
        private Map<Character, Node> childs = new HashMap<Character, Node>();
        
        public int hit() {
            this.count++;
            return this.count;
        }
        
        public Node getChild(char c) {
            return childs.get(c);
        }
    }
    
    public static void main(String[] args) {
        //test
        
        String[] input = new String[] {
                "apple", "app", "approve",
                "hello", "haha"
        };
        Trie t = Trie.build(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String cmd = br.readLine();
                if (cmd.equals("quit")) {
                    break;
                }
                List<String> r = t.findByPrefixAsString(cmd);
                System.out.println(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}