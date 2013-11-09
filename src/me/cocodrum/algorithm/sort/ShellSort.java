/**
 * @(#)HillSort.java, Nov 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class ShellSort extends AbsSort {
    
    private static final int[] STEPS = new int[] {41, 19, 5, 3, 1};

    @Override
    public void sort(int[] input, int start, int end) {
        if (end-start <= 6) {
            new InsertSort().sort(input, start, end);
        }
        
        for (int step:STEPS) {
            for (int s=0; s<step; s++) {
                for (int i=s+step; i<=end; i+=step) {
                    int t = input[i];
                    int j = i-step;
                    while (j>=s && input[j]>t) {
                        input[j+step] = input[j];
                        j = j-step;
                    }
                    input[j+step] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new ShellSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
