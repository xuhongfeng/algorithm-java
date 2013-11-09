/**
 * @(#)SelectSort.java, Nov 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class SelectSort extends AbsSort {

    @Override
    public void sort(int[] input, int start, int end) {
        if (start >= end) return;
        
        for (int i=start; i<end; i++) {
            int min = i;
            for (int j=i+1; j<=end; j++) {
                if (input[j] < input[min]) {
                    min = j;
                }
            }
            swap(input, min, i);
        }
    }

    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new SelectSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
