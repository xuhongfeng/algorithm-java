/**
 * @(#)MaxSubArraySum.java, Aug 24, 2013. 
 * 
 */
package me.cocodrum.algorithm.misc;


/**
 * @author xuhongfeng
 *
 */
public class MaxSubArraySum {
    public Result findMax(int[] a) {
        if (a==null || a.length==0) {
            return Result.NULL;
        }
        
        int n = a.length;
        
        //init result
        Result r = new Result(0, 0, a[0]);
        Result maxEndingHere = r.clone();
        
        for (int i=1; i<n; i++) {
            if (maxEndingHere.max >= 0) {
                maxEndingHere.max += a[i];
                maxEndingHere.end = i;
            } else {
                maxEndingHere.max = a[i];
                maxEndingHere.start = maxEndingHere.end = i;
            }
            if (maxEndingHere.max > r.max) {
                r = maxEndingHere.clone();
            }
        }
        return r;
    }
    
    public static void main(String[] args) {
        int[] a = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        
        Result r = new MaxSubArraySum().findMax(a);
        
        System.out.println(r.start);
        System.out.println(r.end);
        System.out.println(r.max);
    }
    
    private static class Result implements Cloneable {
        public static final Result NULL = new Result(0, 0, 0);
        
        private int max;
        private int start;
        private int end;
        
        public Result(int max, int start, int end) {
            super();
            this.max = max;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public Result clone() {
            return new Result(max, start, end);
        }
    }
}
