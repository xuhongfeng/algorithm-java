/**
 * @(#)InsertSort.java, Sep 14, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class InsertSort extends AbsSort {
    
    @Override
    public void sort(int[] input, int start, int end) {
        if (input == null || end-start<=1) {
            return;
        }
        
        for (int i=start+1; i<=end; i++) {
            int t = input[i];
            int j=i-1;
            while(j>=start && input[j]>t) {
                input[j+1] = input[j];
                j--;
            }
            input[j+1] = t;
        }
    }

    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new InsertSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
