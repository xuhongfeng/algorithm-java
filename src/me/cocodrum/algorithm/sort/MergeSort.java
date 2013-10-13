/**
 * @(#)MergeSort.java, Sep 14, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class MergeSort extends AbsSort {

    @Override
    public void sort(int[] input, int start, int end) {
        if (input == null) {
            return;
        }
        int[] aux = new int[input.length];
        _sort(input, aux, start, end);
    }
    
    private void _sort(int[] input, int[] aux, int start, int end) {
        if (end - start < 8) {
            new InsertSort().sort(input, start, end);
            return;
        }
        int mid = (start + end)/2;
        _sort(input, aux, start, mid);
        _sort(input, aux, mid+1, end);
        merge(input, aux, start, mid, end);
    }

    private void merge(int[] input, int[] aux, int start,
            int mid, int end) {
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
        new MergeSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
