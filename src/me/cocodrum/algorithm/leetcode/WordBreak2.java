package me.cocodrum.algorithm.leetcode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreak2 {

    private Set<String> dict;
    private int n;
    private String s;
    
    private int[][] mem;

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        this.dict = dict;
        this.s = s;
        this.n = s.length();
        mem = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                mem[i][j] = -1;
            }
        }
        
        ArrayList<String> ret = new ArrayList<String>();
        List<String> stack = new LinkedList<String>();
        build(0, ret, stack);
        
        return ret;
    }
    
    private void build(int start, List<String> ret, List<String> stack) {
        for (int i=start; i<n; i++) {
            if (dp(start, i)) {
                String sub = s.substring(start, i+1);
                if (dict.contains(sub)) {
                    stack.add(sub);
//                    System.out.println(stack.size());
//                    System.out.println(start + "," + i + "," + sub);
                    sub = null;
                    if (i == n-1) {
                        StringBuilder sb = new StringBuilder();
                        for (String ss:stack) {
                            if (sb.length() > 0) {
                                sb.append(" ");
                            }
                            sb.append(ss);
                        }
                        ret.add(sb.toString());
                        System.out.println(sb.toString());
                    } else {
                        build(i+1, ret, stack);
                    }
                    stack.remove(stack.size()-1);
                }
            }
        }
    }
    
    private boolean dp(int start, int end) {
        if (mem[start][end] != -1) {
            return mem[start][end]==1? true: false;
        }
        String sub = s.substring(start, end+1);
        if (dict.contains(sub)) {
            mem[start][end] = 1;
            return true;
        }
        for (int i=start; i<end; i++) {
            if (dp(start, i) && dp(i+1, end)) {
                mem[start][end] = 1;
                return true;
            }
        }
        mem[start][end] = 0;
        return false;
    }

    public static void main(String[] args) {
        String s="";
        String[] array = new String[0];
        Set<String> dict = new HashSet<String>();
        for (String a:array) {
            dict.add(a);
        }
        new WordBreak2().wordBreak(s, dict);
    }
}