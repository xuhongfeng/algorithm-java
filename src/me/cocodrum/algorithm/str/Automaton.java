/**
 * @(#)Automaton.java, Sep 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author xuhongfeng
 *
 */
public class Automaton {

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
        
        //build charset
        Set<Character> set = new HashSet<Character>();
        for (char c:a) {
            set.add(c);
        }
        for (char c:b) {
            set.add(c);
        }
        char[] charset = new char[set.size()];
        int i =0;
        Iterator<Character> it = set.iterator();
        while (it.hasNext()) {
            charset[i++] = it.next();
        }
        
        Matcher matcher = new Matcher();
        matcher.build(charset, b);
        for (i=0; i<n; i++) {
            matcher.consume(a[i]);
            if (matcher.match()) {
                return i - m + 1;
            }
        }
        
        return -1;
    }
    
    private class Matcher {
        private Map<Integer, Integer> trans;
        private int state = 0;
        private int m;
        
        public void consume(char c) {
            int hash = hash(this.state, c);
            this.state = trans.get(hash);
        }
        
        public boolean match() {
            return this.state == m;
        }
        
        private int hash(int state, char c) {
            return state * 31 + c;
        }
        
        public void build(char[] charset, char[] pattern) {
            m = pattern.length;
            
            //build trans
            trans = new HashMap<Integer, Integer>();
            for (int state=0; state<m; state++) {
                for (char c:charset) {
                    //compute trans(state, c) -> next
                    int next = state+1;
                    while (next > 0) {
                        int len = next;
                        boolean match = true;
                        for (int i=0; i<len; i++) {
                            char c1 = pattern[i];
                            int start = state +1 - len;
                            char c2 = i==len-1 ? c : pattern[start+i];
                            if (c1 != c2) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            break;
                        }
                        next--;
                    }
                    
                    int hash = hash(state, c);
                    trans.put(hash, next);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        String pattern = scanner.next();
        System.out.println(new Automaton().indexOf(text, pattern));
    }
}
