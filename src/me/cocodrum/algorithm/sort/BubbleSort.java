/**
 * @(#)BubbleSort.java, Nov 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class BubbleSort extends AbsSort {

    @Override
    public void sort(int[] input, int start, int end) {
        for (int i=end; i>start; i--) {
            for (int j=start; j<i; j++) {
                if (input[j] > input[j+1]) {
                    swap(input, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new BubbleSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
