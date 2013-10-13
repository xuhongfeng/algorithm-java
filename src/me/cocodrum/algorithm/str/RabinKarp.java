/**
 * @(#)RabinKarp.java, Sep 19, 2013. 
 * 
 */
package me.cocodrum.algorithm.str;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class RabinKarp {

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
        Map<Character, Integer> dict = buildDict(a, b);
        int d = dict.size();
        int h = Utils.pow(d, m-1);
        
        int p = 0;
        int t = 0;
        for (int i=0; i<m; i++) {
            t = d*t + dict.get(a[i]);
            p = d*p + dict.get(b[i]);
        }
        for (int i=m-1; i<n; i++) {
            if (i != m-1) {
                t = d*(t - dict.get(a[i-m])*h) + dict.get(a[i]);
            }
            if (t==p && match(a, i-m+1, b)) {
                return i-m+1;
            }
        }
        
        return -1;
    }
    
    private boolean match(char[] a, int start, char[]b) {
        for (int i=0; i<b.length; i++) {
            if (a[start+i] != b[i]) {
                return false;
            }
        }
        return true;
    }
    
    private Map<Character, Integer> buildDict(char[]... chars) {
        Map<Character, Integer> dict = new HashMap<Character, Integer>();
        int k = 0;
        for (char[] array:chars) {
            for (char c:array) {
                if (!dict.containsKey(c)) {
                    dict.put(c, k++);
                }
            }
        }
        return dict;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        String pattern = scanner.next();
        System.out.println(new RabinKarp().indexOf(text, pattern));
    }
}
