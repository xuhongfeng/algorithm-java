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
        if (input == null) {
            return;
        }
        
        for (int i=start+1; i<=end; i++) {
            for (int j=i-1; j>=start && input[j] > input[j+1]; j--) {
                swap(input, j, j+1);
            }
        }
    }

    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new InsertSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
