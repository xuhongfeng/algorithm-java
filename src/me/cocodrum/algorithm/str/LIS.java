/**
 * @(#)LIS.java, Sep 14, 2013.
 */
package me.cocodrum.algorithm.str;

import java.util.Arrays;

/**
 * @author xuhongfeng
 */
public class LIS {

    private int[] input;

    private int[] m; // m[i] is the last element’s index of the longest
                     // subsequence which length is i

    private int[] p; // p[i] is the i’th element’s predecessor in the longest
                     // subsequence

    private int n;

    private int max;

    public int[] solve(int[] input) {
        if (input == null) {
            return null;
        }
        this.input = input;
        n = input.length;
        m = new int[n + 1];
        p = new int[n];
        max = 0;
        for (int i = 0; i < n; i++) {
            int value = input[i];
            int k = binarySearch(value); // find maximum k that k<max and
                                         // input[m[k]] <= value, return 0 if
                                         // no such k
            int len = k + 1;
            if (len > max) {
                max = len;
                m[len] = i;
            } else if (input[m[len]] > value) {
                m[len] = i;
            }
            p[i] = m[len - 1];
        }
        int[] r = new int[max];
        int i = m[max], k = max - 1;
        while (k >= 0) {
            r[k--] = input[i];
            i = p[i];
        }
        return r;
    }

    private int binarySearch(int value) {
        int low = 1, high = max;
        int mid = 1;
        while (low <= high) {
            mid = (low + high) / 2;
            int v = input[m[mid]];
            if (v <= value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low - 1;
    }

    public static void main(String[] args) {
        int[] input = new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int[] r = new LIS().solve(input);
        System.out.println(Arrays.toString(r));
    }
}
