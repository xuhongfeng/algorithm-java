/**
 * @(#)MergeSort2.java, Nov 3, 2013. 
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
        if (start >= end) return;
        
        int[] aux = new int[input.length];
        _sort(input, aux, start, end);
    }
    
    private void _sort(int[] input, int[] aux, int start, int end) {
        if (end-start<=6) {
            new InsertSort().sort(input, start, end);
            return;
        }
        
        int mid = (start+end)/2;
        _sort(input, aux, start, mid);
        _sort(input, aux, mid+1, end);
        if (input[mid] <= input[mid+1]) {
            return;
        }
        merge(input, aux, start, end, mid);
    }
    
    private void merge(int[] input, int[] aux, int start, int end, int mid) {
        for (int i=start; i<=end; i++) {
            aux[i] = input[i];
        }
        
        int k = start, i=start, j=mid+1;
        while (i<=mid && j<=end) {
            if (aux[i] < aux[j]) {
                input[k++] = aux[i++];
            } else {
                input[k++] = aux[j++];
            }
        }
        while (i<=mid) {
            input[k++] = aux[i++];
        }
        while (j<=end) {
            input[k++] = aux[j++];
        }
    }
    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new MergeSort().sort(a);
        System.out.print(Arrays.toString(a));
    }

}
