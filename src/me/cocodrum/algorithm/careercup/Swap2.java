/**
 * @(#)Swap2.java, Sep 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.careercup;

import java.util.Arrays;

/**
 * 
 * http://www.careercup.com/question?id=5201559730257920
 * 
 * @author xuhongfeng
 *
 */
public class Swap2 {
    private int[] input;
    private int n;
    
    public void resolve(int[] input) {
        if (input == null || input.length==0) {
            return;
        }
        this.input = input;
        this.n = input.length;
        
        int start=0, end=n-1;
        while (start < end) {
            boolean swap = false;
            for (int i=start; i<end; i++) {
                if (input[i]>=0 && input[i+1]<0) {
                    swap(i, i+1);
                    end = i;
                    if (!swap) {
                        swap = true;
                    }
                } else if (!swap && input[i]<0) {
                    start = i+1;
                }
            }
            if (!swap) {
                break;
            }
        }
    }
        
    public void resolve2(int[] input) {
        if (input == null || input.length==0) {
            return;
        }
        this.input = input;
        this.n = input.length;
        innerResolve2(input, 0, n-1);
    }
    
    private void innerResolve2(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int countNegative = 0;
        for (int i=start; i<=end; i++) {
            if (a[i] < 0) {
                countNegative++;
            }
        }
        int mid = start+countNegative;
        int i=start, j = mid;
        int hit = 0;
        while (i<mid && j<=end) {
            while (a[i]<0 && i<mid) i++;
            if (i==mid) {
                break;
            }
            while (a[j] >= 0) j++;
            swap(i, j);
            a[i] = -a[i];
            a[j] = -a[j];
            i++;
            j++;
            hit++;
        }
        innerResolve2(a, start, mid-1);
        for (i=mid-hit; i<mid; i++) {
            a[i] = - a[i];
        }
        innerResolve2(a, mid, end);
        for (i=mid; i<=mid+hit-1; i++) {
            a[i] = - a[i];
        }
    }

    private void swap(int i, int j) {
        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }
    
    public static void main(String[] args) {
        int[] input = new int[]{-1, 1, 3, -2, 2};
        System.out.println(Arrays.toString(input));
        new Swap2().resolve2(input);
        System.out.println(Arrays.toString(input));
    }
}
