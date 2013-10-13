package me.cocodrum.algorithm.leetcode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        if (start.equals(end)) {
            ArrayList<String> subList = new ArrayList<String>(1);
            subList.add(start);
            ret.add(subList);
            return ret;
        }
        
        int n=start.length();
        Map<Integer, Node> visited = new HashMap<Integer, Node>();
        List<Node> queue = new LinkedList<Node>();
        
        Node p = new Node(start, 1);
        queue.add(p);
        visited.put(start.hashCode(), p);
        
        int d = -1;
        while (queue.size() > 0) {
            p = queue.remove(0);
            if (d!=-1 && p.distance>=d) continue;
            
            char[] a = p.value.toCharArray();
            for (int i=0; i<n; i++) {
                char old = a[i];
                for (int j=1; j<26; j++) {
                    if (a[i] == 'z') {
                        a[i] = 'a';
                    } else {
                        a[i] = (char)(a[i]+1);
                    }
                    String s = new String(a);
                    if (s.equals(end)) {
                        d = p.distance+1;
                        ArrayList<String> subList = new ArrayList<String>(d);
                        subList.add(s);
                        Node t = p;
                        while (t != null) {
                            subList.add(0, t.value);
                            t = t.parent;
                        }
                        ret.add(subList);
                        continue;
                    }
                    if (d!=-1) continue;
                    if (dict.contains(s)) {
                        int h = s.hashCode();
                        if (!visited.containsKey(h)) {
                            Node q = new Node(s, p.distance+1);
                            q.parent = p;
                            visited.put(h, q);
                            queue.add(q);
                        }
                    }
                }
                a[i] = old;
            }
        }
        
        return ret;
    }
    
    private class Node {
        String value;
        int distance;
        Node parent;
        
        public Node (String value, int d) {
            this.value = value;
            this.distance = d;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (parent != null) {
                sb.append(parent.toString());
                sb.append(",");
            }
            sb.append(value);
            return sb.toString();
        }

        
    }
    
    public static void main(String[] args) {
        String[] s = new String[] {"ted","tex","red","tax","tad","den","rex","pee"};
        String start = "red", end="tax";
        
        HashSet<String> dict = new HashSet<String>();
        for (String ss:s) {
            dict.add(ss);
        }
        
        ArrayList<ArrayList<String>> ret = new Solution().findLadders(start, end, dict);
        for (ArrayList<String> list:ret) {
            System.out.println(list);
        }
    }
}