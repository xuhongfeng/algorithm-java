/**
 * @(#)CountSort.java, Nov 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class CountSort {

    public void sort(int[] input, int max) {
        int[] c = new int[max+1];
        for (int i=0; i<=max; i++) {
            c[i] = 0;
        }
        for (int t:input) {
            c[t]++;
        }
        for (int i=1; i<=max; i++) {
            c[i] += c[i-1];
        }
        
        int[] aux = Arrays.copyOf(input, input.length);
        
        for (int t:aux) {
            input[--c[t]] = t;
        }
    }

    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(50, 20);
        new CountSort().sort(a, 20);
        System.out.print(Arrays.toString(a));
    }
}
