/**
 * @(#)BottomUpMergeSort2.java, Nov 3, 2013. 
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

    @Override
    public void sort(int[] input, int start, int end) {
        if (end-start <= 6) {
            new InsertSort().sort(input, start, end);
            return;
        }
        int n = end-start+1;
        int[] aux = new int[input.length];
        
        int step = 2;
        while (true) {
            for (int s=start; s<=end; s+=step) {
                int e = s+step-1;
                if (e > end) e=end;
                int mid = s+step/2-1;
                if (mid >= e) break;
                if (input[mid] > input[mid+1]) {
                    merge(input, aux, s, e, mid);
                }
            }
            if (step >= n) {
                break;
            }
            step = 2*step;
        }
    }
    
    private void merge(int[] input, int[] aux, int start, int end, int mid) {
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
