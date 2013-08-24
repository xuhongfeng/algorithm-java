/**
 * @(#)MaxDifference.java, Aug 24, 2013. 
 * 
 */
package me.cocodrum.algorithm.careercup;


/**
 * http://www.careercup.com/question?id=19286747
 * 
 * @author xuhongfeng
 *
 */
public class MaxDifference {
    
    private final MidResult NULL_MID = new MidResult();
    public final Result NULL_RESULT = new Result();

    private MidResult[] minLeft;
    private MidResult[] maxLeft;
    private MidResult[] minRight;
    private MidResult[] maxRight;
    
    private int[] input;
    
    public Result exec(int[] input) {
        if (input==null || input.length==0) {
            return NULL_RESULT;
        }
        this.input = input;
        int n = input.length;
        preProcess();//O(n)
        Result r = new Result();
        r.max = Integer.MIN_VALUE;
        for (int i=0; i<=n; i++) {
            MidResult small = i==0 ? NULL_MID : minLeft[i-1];
            MidResult big = i==n ? NULL_MID : maxRight[i];
            Result t = new Result(small, big);
            if (t.max > r.max) {
                r = t;
            }
            
            small = i==n ? NULL_MID : minRight[i];
            big = i==0 ? NULL_MID : maxLeft[i-1];
            t = new Result(small, big);
            if (t.max > r.max) {
                r = t;
            }
        }
        return r;
    }
    
    private void preProcess() {
        int n = input.length;
        minLeft = new MidResult[n];
        minRight = new MidResult[n];
        maxLeft = new MidResult[n];
        maxRight = new MidResult[n];
        
        MidResult minEndingHere = null, maxEndingHere = null;
        for (int i=0; i<n; i++) {
            if (i == 0) {
                minEndingHere = new MidResult(0);
                maxEndingHere = minEndingHere.clone();
                minLeft[0] = minEndingHere.clone();
                maxLeft[0] = minEndingHere.clone();
            } else {
                if (minEndingHere.value < 0) {
                    minEndingHere.value += input[i];
                    minEndingHere.end = i;
                } else {
                    minEndingHere = new MidResult(i);
                }
                if (minEndingHere.value < minLeft[i-1].value) {
                    minLeft[i] = minEndingHere.clone();
                } else {
                    minLeft[i] = minLeft[i-1].clone();
                }
                if (maxEndingHere.value > 0) {
                    maxEndingHere.value += input[i];
                    maxEndingHere.end = i;
                } else {
                    maxEndingHere = new MidResult(i);
                }
                if (maxEndingHere.value > maxLeft[i-1].value) {
                    maxLeft[i] = maxEndingHere.clone();
                } else {
                    maxLeft[i] = maxLeft[i-1].clone();
                }
            }
        }
        
        for (int i=n-1; i>=0; i--) {
            if (i == n-1) {
                minEndingHere = new MidResult(i);
                maxEndingHere = minEndingHere.clone();
                minRight[i] = minEndingHere.clone();
                maxRight[i] = minEndingHere.clone();
            } else {
                if (minEndingHere.value < 0) {
                    minEndingHere.value += input[i];
                    minEndingHere.start = i;
                } else {
                    minEndingHere = new MidResult(i);
                }
                if (minEndingHere.value < minRight[i+1].value) {
                    minRight[i] = minEndingHere.clone();
                } else {
                    minRight[i] = minRight[i+1].clone();
                }
                if (maxEndingHere.value > 0) {
                    maxEndingHere.value += input[i];
                    maxEndingHere.start = i;
                } else {
                    maxEndingHere = new MidResult(i);
                }
                if (maxEndingHere.value > maxRight[i+1].value) {
                    maxRight[i] = maxEndingHere.clone();
                } else {
                    maxRight[i] = maxRight[i+1].clone();
                }
            }
        }
    }
    
    public class Result {

        private int max;
        private MidResult small;
        private MidResult big;
        
        public Result() {}
        
        public Result(MidResult small, MidResult big) {
            this.small = small;
            this.big = big;
            if (small == NULL_MID) {
                max = big.value;
            } else {
                max = big.value - small.value;
            }
        }

        @Override
        public String toString() {
            return "Result [max=" + max + ", small=" + small + ", big=" + big
                    + "]";
        }
        
    }

    public class MidResult implements Cloneable {
        
        private int value;
        private int start;
        private int end;
        
        public MidResult(int value, int start, int end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }
        
        public MidResult() {
        }
        
        public MidResult (int index) {
            this(input[index], index, index);
        }
        
        @Override
        public MidResult clone() {
            return new MidResult(value, start, end);
        }

        @Override
        public String toString() {
            return "MidResult [value=" + value + ", start=" + start + ", end="
                    + end + "]";
        }
    }
    
    public static void main(String[] args) {
        int input[] = new int[] {2, -1, -2, 1, -4, 2, 8};
        Result r = new MaxDifference().exec(input);
        System.out.println(r);
    }
}
