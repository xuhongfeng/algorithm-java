/**
 * @(#)HeapSort.java, Nov 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class HeapSort extends AbsSort {

    @Override
    public void sort(int[] input, int start, int end) {
        if (end - start <= 6) {
            new InsertSort().sort(input, start, end);
            return;
        }
        
        Heap heap = new Heap(input, start, end);
        while (heap.size > 0) {
            heap.swapMax();
        }
        for (int i=0; i<end-start+1; i++) {
            input[start+i] = heap.values[i];
        }
    }
    
    private class Heap {
        private int[] values;
        private int size;
        
        private Heap(int[] a, int start, int end) {
            size = end-start+1;
            values = new int[size];
            for (int i=0; i<size; i++) {
                values[i] = a[start+i];
            }
            for (int i=size/2; i>=0; i--) {
                heapifyDown(i);
            }
        }
        
        private void swapMax() {
            swap(values, 0, size-1);
            size--;
            heapifyDown(0);
        }
        
        private void heapifyDown(int i) {
            int max=i;
            int left = 2*i+1;
            int right = left+1;
            
            if (left < size) {
                if (values[left] > values[max]) {
                    max = left;
                }
                if (right < size && values[right]>values[max]) {
                    max = right;
                }
            }
            if (max != i) {
                swap(values, max, i);
                heapifyDown(max);
            }
        }
        
    }
    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new HeapSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
