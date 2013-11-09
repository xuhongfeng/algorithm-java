/**
 * @(#)BitmapSort.java, Nov 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class BitmapSort extends AbsSort {

    @Override
    public void sort(int[] input, int start, int end) {
        int a = 0;
        for (int i=start; i<=end; i++) {
            int v = input[i];
            a |= (1<<v);
        }
        
        int k = 0;
        for (int i=start; i<=end; i++) {
            while ( ((a>>k)&1) == 0) {
                k++;
            }
            input[i] = k++;
        }
    }

    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new BitmapSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
