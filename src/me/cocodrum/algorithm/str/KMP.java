/**
 * @(#)KMP.java, Sep 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.str;

import java.util.Scanner;

/**
 * @author xuhongfeng
 *
 */
public class KMP {

    public int indexOf(String text, String pattern) {
        if (text==null || text.length()==0
                || pattern==null || pattern.length()==0
                || pattern.length() > text.length()) {
            return -1;
        }
        char[] a = text.toCharArray();
        int n = a.length;
        char[] b = pattern.toCharArray();
        int m = b.length;
        Matcher matcher = new Matcher();
        matcher.build(b);
        for (int i=0; i<n; i++) {
            matcher.consume(a[i]);
            if (matcher.match()) {
                return i - m + 1;
            }
        }
        
        return -1;
    }
    
    private class Matcher {
        private int state;
        private int[] pi;
        private int m;
        private char[] pattern;
        
        public void consume(char c) {
            if (pattern[state] == c) {
                state++;
            } else{
                state = pi[state];
            }
        }
        
        public boolean match() {
            return state == m;
        }
        
        public void build(char[] pattern) {
            this.pattern = pattern;
            m = pattern.length;
            pi = new int[m];
            
            int k = 0;//back to this state
            for (int q=1; q<m; q++) {
                while (k>0 && pattern[k]!=pattern[q-1]) {
                    k = pi[k];
                }
                if (pattern[k] == pattern[q-1]) {
                    k++;
                }
                pi[q] = k;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        String pattern = scanner.next();
        System.out.println(new KMP().indexOf(text, pattern));
    }
}
