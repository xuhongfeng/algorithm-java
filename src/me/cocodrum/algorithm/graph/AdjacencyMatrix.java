/**
 * @(#)AdjacencyMatrix.java, Sep 15, 2013. 
 * 
 */
package me.cocodrum.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

import me.cocodrum.algorithm.util.Utils;

/**
 * @author xuhongfeng
 *
 */
public class AdjacencyMatrix extends AbsGraph {
    protected final int[][] matrix;
    
    public AdjacencyMatrix(int n) {
        this(n, Integer.MAX_VALUE);
    }
    
    public AdjacencyMatrix(int n, int initValue) {
        super(n);
        matrix = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = initValue;
                }
            }
        }
    }
    
    @Override
    public void setWeight(int i, int j, int weight) {
        matrix[i][j] = weight;
    }

    @Override
    public int[] getAdjacency(int vertex) {
        int n = size();
        List<Integer> list = new LinkedList<Integer>();
        for (int i=0; i<n; i++) {
            if (i!=vertex && linked(vertex, i)) {
                list.add(i);
            }
        }
        return Utils.listToArray(list);
    }
    
    public int weight(int i, int j) {
        return matrix[i][j];
    }

}
