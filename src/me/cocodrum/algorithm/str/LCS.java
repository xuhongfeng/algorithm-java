/**
 * @(#)LCS.java, Sep 13, 2013. 
 * 
 */
package me.cocodrum.algorithm.str;

/**
 * @author xuhongfeng
 *
 */
public class LCS {
    private static final Result NullResult = new Result(0, "");
    
    private static enum Choice {
        TOP, LEFT, BOTH
    };
    
    private char[] a, b;
    private int n1, n2;
    private int[][] mem;
    private Choice[][] choices;

    public Result solve(String s1, String s2) {
        if (s1==null || s1.length()==0
                || s2==null || s2.length()==0) {
            return NullResult;
        }
        
        a = s1.toCharArray();
        n1 = a.length;
        b = s2.toCharArray();
        n2 = b.length;
        
        mem = new int[n1][n2];
        choices = new Choice[n1][n2];
        for (int i=0; i<n1; i++) {
            for (int j=0; j<n1; j++) {
                mem[i][j] = -1;
            }
        }
        
        int length = _lcs(n1-1, n2-1);
        String str = buildSubSequence();
        
        return new Result(length, str);
    }
    
    private String buildSubSequence() {
        StringBuilder sb = new StringBuilder();
        int i=n1-1, j=n2-1;
        while (i>=0 && j>=0) {
            Choice c = choices[i][j];
            if (c == Choice.BOTH) {
                sb.insert(0, a[i]);
                i--;
                j--;
            } else if (c == Choice.LEFT) {
                j--;
            } else {
                i--;
            }
        }
        return sb.toString();
    }
    
    private int _lcs(int i, int j) {
        if (i<0 || j<0) {
            return 0;
        }
        if (mem[i][j] != -1) {
            return mem[i][j];
        }
        if (a[i] == b[j]) {
            int len = _lcs(i-1, j-1) + 1;
            mem[i][j] = len;
            choices[i][j] = Choice.BOTH;
            return len;
        }
        int left = _lcs(i, j-1);
        int top = _lcs(i-1, j);
        if (left > top) {
            mem[i][j] = left;
            choices[i][j] = Choice.LEFT;
            return left;
        } else {
            mem[i][j] = top;
            choices[i][j] = Choice.TOP;
            return top;
        }
    }
    
    
    public static void main(String[] args) {
        Result r = new LCS().solve("XMJYAUZ", "MZJAWXU");
        System.out.println(r);
    }
    
    private static class Result {
        private int length;
        private String subSequence;
        
        public Result(int length, String subSequence) {
            super();
            this.length = length;
            this.subSequence = subSequence;
        }

        @Override
        public String toString() {
            return "Result [length=" + length + ", subSequence=" + subSequence
                    + "]";
        }
    }
}
