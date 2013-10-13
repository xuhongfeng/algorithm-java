/**
 * @(#)BottomUpMergeSort.java, Sep 14, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class BottomUpMergeSort extends AbsSort {
    private int[] aux;

    @Override
    public void sort(int[] input, int start, int end) {
        if (input==null || end-start<=0) {
            return;
        }
        aux = new int[input.length];
        int n = end - start + 1;
        
        for (int step=2; step<=2*n; step *=2) {
            int k = step/2;
            for (int i=start; i<=end; i+=step) {
                int mid = i+k-1;
                if (mid < end) {
                    int j = mid + k;
                    if (j > end) {
                        j = end;
                    }
                    merge(input, i, mid, j);
                }
            }
            
            if (step == n) {
                break;
            }
        }
    }

    private void merge(int[] input, int start, int mid, int end) {
        //copy to aux
        for (int i=start; i<=end; i++) {
            aux[i] = input[i];
        }
        int i=start, j=mid+1, k=start;
        while(i<=mid && j<=end) {
            if (aux[i] < aux[j]) {
                input[k++] = aux[i++];
            } else {
                input[k++] = aux[j++];
            }
        }
        while(i<=mid) {
            input[k++] = aux[i++];
        }
        while(j<=end) {
            input[k++] = aux[j++];
        }
    }
    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new BottomUpMergeSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
