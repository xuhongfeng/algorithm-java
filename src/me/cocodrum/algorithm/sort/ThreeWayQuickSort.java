/**
 * @(#)ThreeWayQuickSort.java, Sep 20, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class ThreeWayQuickSort {

    public void sort(int[] input) {
        if (input==null || input.length<=1) {
            return;
        }
        _sort(input, 0, input.length-1);
    }
    
    private void _sort(int[] a, int start, int end) {
        if (end <= start) {
            return;
        }
        if (end-start < 8) {
            new InsertSort().sort(a, start, end);
            return;
        }
        int lt=start, gt=end;
        int p = mid(a, start, end, (start+end)/2);
        int v = a[p];
        int i = lt;
        while (i <= gt) {
            if (a[i] < v) {
                Utils.swap(a, i++, lt++);
            } else if (a[i] > v) {
                Utils.swap(a, i, gt--);
            } else {
                i++;
            }
        }
        _sort(a, start, lt-1);
        _sort(a, gt+1, end);
    }
    
    private int mid (int[] array, int i, int j, int k) {
        int a = array[i];
        int b = array[j];
        int c = array[k];
        if (a<b) {
            if (c < a) {
                return i;
            } else if (c < b) {
                return k;
            }
            return j;
        } else {
            if (c < b) {
                return j;
            } else if (c < a) {
                return k;
            }
            return i;
        }
    }
    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100, 20);
        new ThreeWayQuickSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
