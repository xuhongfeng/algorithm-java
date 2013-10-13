/**
 * @(#)Utils.java, Sep 14, 2013. 
 * 
 */
package me.cocodrum.algorithm.util;

import java.util.List;
import java.util.Random;

/**
 * @author xuhongfeng
 *
 */
public class Utils {

    public static int[] generateIntArray(int n) {
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = i;
        }
        shuffle(a);
        return a;
    }
    
    public static int[] generateIntArray(int n, int max) {
        Random random = new Random();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = random.nextInt(max+1);
        }
        return a;
    }
    
    public static void shuffle(int[] a) {
        Random random = new Random();
        for (int i=0; i<a.length; i++) {
            swap(a, i, random.nextInt(a.length));
        }
    }
    
    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    public static int[] listToArray(List<Integer> list) {
        int[] a = new int[list.size()];
        for (int i=0; i<a.length; i++) {
            a[i] = list.get(i);
        }
        return a;
    }
    
    public static int pow(int d, int k) {
        if (k == 0) {
            return 1;
        }
        int e = (int) (Math.log(k) / Math.log(2));
        int[] t = new int[e+1];
        for (int i=e; i>=0 && k!=0; i--) {
            int h = pow(2, i);
            t[i] = k/h;
            k = k - t[i]*h;
        }
        int r = 1, h = d;
        for (int i=0; i<t.length; i++) {
            if (t[i] != 0) {
                r = r * t[i] * h;
            }
            h = h*h;
        }
        return r;
    }
}