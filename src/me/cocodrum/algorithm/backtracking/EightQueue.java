/**
 * @(#)EightQueue.java, Sep 22, 2013. 
 * 
 */
package me.cocodrum.algorithm.backtracking;


/**
 * @author xuhongfeng
 *
 */
public class EightQueue {
    private int n;
    private int count;
    private boolean[] rows;
    private boolean[] columns;
    private boolean[] diagonal1;
    private boolean[] diagonal2;

    public int solve(int n) {
        if (n <= 0) {
            return 0;
        }
        this.n = n;
        this.count = 0;
        this.rows = new boolean[n];
        this.columns = new boolean[n];
        this.diagonal1 = new boolean[2*n-1];
        this.diagonal2 = new boolean[2*n-1];
        
        backtracking(0);
        
        return this.count;
    }
    
    private void backtracking(int column) {
        for (int row=0; row<n; row++) {
            if(canFill(column, row)) {
                fill(column, row);
                if (column == n-1) {
                    count++;
                } else {
                    backtracking(column+1);
                }
                unfill(column, row);
            }
        }
    }
    
    private void unfill(int column, int row) {
        this.setFill(column, row, false);
    }
    
    private void setFill(int column, int row, boolean isFill) {
        this.columns[column] = isFill;
        this.rows[row] = isFill;
        
        this.diagonal1[n-1+row-column] = isFill;
        this.diagonal2[row+column] = isFill;
    }
    
    private void fill(int column, int row) {
        this.setFill(column, row, true);
    }
    
    private boolean canFill(int column, int row) {
        return !this.columns[column]
                && !this.rows[row]
                && !this.diagonal1[n-1+row-column]
                && !this.diagonal2[column+row];
    }
    
    public static void main(String[] args) {
        System.out.println(new EightQueue().solve(8));
    }
}
