/**
 * @(#)MaxCoins.java, Aug 21, 2013. 
 * 
 */
package me.cocodrum.algorithm.careercup;

/**
 * http://www.careercup.com/question?id=15422849
 * 
 * @author xuhongfeng
 *
 */
public abstract class MaxCoins {
    private static final int[] coins = new int[] {6, 5, 1, 7, 3, 4, 8, 9, 2};
    private static final int n = coins.length;
    protected int[] choice = new int[coins.length];

    protected abstract int maxCoins(int start, int end);
    
    protected int max(int a , int b) {
        return a > b ? a : b;
    }
    
    protected int[][] sum = new int[n][n];
    protected int sum(int start, int end) {
        if (start > end) {
            return 0;
        }
        if (sum[start][end] > 0) {
            return sum[start][end];
        }
        if (start == end) {
            sum[start][end] = coins[start];
            return sum[start][end];
        }
        sum[start][end] = sum(start, end-1) + coins[end];
            return sum[start][end];
    }
    
    public void run() {
        System.out.println(maxCoins(0, coins.length-1));
        printChoice();
    }
    
    protected void printChoice() {
        for (int i=0; i<choice.length; i+=2) {
            int c = choice[i];
            System.out.print(coins[c] + "  ");
        }
        System.out.println();
        for (int i=1; i<choice.length; i+=2) {
            int c = choice[i];
            System.out.print(coins[c] + "  ");
        }
        System.out.println();
    }
    
    private static class Recursive extends MaxCoins {
        private int step = 0;
        
        @Override
        protected int maxCoins(int start, int end) {
            if (start > end) {
                return 0;
            }
            if (start == end) {
                choice[step++] = start;
                return coins[start];
            }
            int total = sum(start, end);
            int stepNow = step++;
            int a = total-maxCoins(start+1, end);
            step = stepNow + 1;
            int[] oldChoice = new int[choice.length - step];
            for (int i=0; i<oldChoice.length; i++) {
                oldChoice[i] = choice[step+i];
            }
            int b = total-maxCoins(start, end-1);
            if (a > b) {
                choice[stepNow] = start;
                for (int i=0; i<oldChoice.length; i++) {
                    choice[stepNow+i+1] = oldChoice[i];
                }
                return a;
            } else {
                choice[stepNow] = end;
                return b;
            }
        }
    }
    
    private static abstract class BaseDp extends MaxCoins {
        protected int[][] mem = new int[n][n];
        protected int[][] c = new int[n][n];
        
        @Override
        protected void printChoice() {
            int start=0, end=n-1, k=0;
            while (k < n) {
                choice[k++] = c[start][end];
                if (c[start][end] == start) {
                    start++;
                } else {
                    end--;
                }
            }
            super.printChoice();
        }
    }
    
    private static class Dp1 extends BaseDp {
        @Override
        protected int maxCoins(int start, int end) {
            if (start > end) {
                return 0;
            }
            if (mem[start][end] == 0) {
                if (start == end) {
                    c[start][end] = start;
                    mem[start][end] = coins[start];
                } else {
                    int total = sum(start, end);
                    int a = total - maxCoins(start + 1, end);
                    int b = total - maxCoins(start, end - 1);
                    if (a > b) {
                        mem[start][end] = a;
                        c[start][end] = start;
                    } else {
                        mem[start][end] = b;
                        c[start][end] = end;
                    }
                }
            }
            return mem[start][end];
        }
    }
    
    private static class Dp2 extends BaseDp {
        @Override
        protected int maxCoins(int start, int end) {
            for (int k=0; k<=n-1; k++) {
                for (int i=0; i+k<=n-1; i++) {
                    if (k == 0) {
                        mem[i][i] = coins[i];
                        c[i][i] = i;
                    } else {
                        int j = i+k;
                        int total = sum(i, j);
                        int a = total - mem[i+1][j];
                        int b = total - mem[i][j-1];
                        if (a > b) {
                            mem[i][j] = a;
                            c[i][j] = i;
                        } else {
                            mem[i][j] = b;
                            c[i][j] = j;
                        }
                    }
                }
            }
            return mem[0][n-1];
        }
    }
    
    public static void main(String[] args) {
        new Recursive().run();
        new Dp1().run();
        new Dp2().run();
    }
}