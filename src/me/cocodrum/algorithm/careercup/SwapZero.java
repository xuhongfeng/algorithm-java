/**
 * @(#)SwapZero.java, Sep 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.careercup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * http://www.careercup.com/question?id=5700226908160000
 * 
 * @author xuhongfeng
 *
 */
public class SwapZero {

    private int[] src, dest;

    public void solve(int[] src, int dest[]) {
        this.src = src;
        this.dest = dest;

        //map the value of dest to it's index
        Map<Integer, Integer> dict = new HashMap<Integer, Integer>();
        for (int i=0; i<dest.length; i++) {
            dict.put(dest[i], i);
        }
        
        for (int i=src.length-1; i>=0; i--) {
            
            int value = src[i];
            int index = dict.get(value);
            while (index != i) {
                swap(0, i);
                swap(0, index);
                value = src[i];
                index = dict.get(value);
            }
        }
    }
    
    private void swap(int i, int j) {
        int t = src[i];
        src[i] = src[j];
        src[j] = t;
    }
    
    public static void main(String[] args) {
        int[] a = new int[] {31,53,5,29,7,34, 67, 89, 234};
        int[] b = new int[] {53, 31, 7, 89, 29, 5, 34, 234, 67};
        
        new SwapZero().solve(a, b);
        
        System.out.println(Arrays.toString(a));
    }
}
