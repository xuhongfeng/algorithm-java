/**
 * @(#)AbsSort.java, Sep 14, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public abstract class AbsSort implements Sort {

    @Override
    public void sort(int[] input) {
        if (input == null) {
            return;
        }
        sort(input, 0, input.length-1);
    }

    protected void swap(int[] a, int i, int j) {
        Utils.swap(a, i, j);
    }
}
