/**
 * @(#)QuickSort.java, Sep 20, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class QuickSort {

    public void sort(int[] input) {
        if (input==null || input.length<=1) {
            return;
        }
        _sort(input, 0, input.length-1);
    }
    
    private void _sort(int[] a, int start, int end) {
        if (end-start < 8) {
            new InsertSort().sort(a, start, end);
            return;
        }
        int p = partition(a, start, end);
        _sort(a, start, p-1);
        _sort(a, p+1, end);
    }
    
    private int partition(int[] a, int start, int end) {
        int p = mid(a, start, end, (start+end)/2);
        Utils.swap(a, start, p);
        int i=start+1, j=end;
        int v = a[start];
        while (i<=j) {
            while (i<=j && a[i]<=v) {
                i++;
            }
            while (i<=j && a[j]>=v) {
                j--;
            }
            if (i < j) {
                Utils.swap(a, i, j);
                i++;
                j--;
            }
        }
        Utils.swap(a, i-1, start);
        return i-1;
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
        new QuickSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
