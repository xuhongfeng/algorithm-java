/**
 * @(#)MinRange.java, Aug 27, 2013. 
 * 
 */
package me.cocodrum.algorithm.careercup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import me.cocodrum.algorithm.structure.Heap;

/**
 * @author xuhongfeng
 * 
 * http://www.careercup.com/question?id=16759664
 *
 */
public class MinRange {
    private final List<List<Integer>> input;
    
    public MinRange(List<List<Integer>> input) {
        super();
        this.input = input;
    }

    public Result exec() {
        MyHeap heap = new MyHeap();
        for (List<Integer> list:input) {
            Node node = new Node(list, 0);
            heap.insert(node);
        }
        Result ret = heap.exportResult();
        while (true) {
            Node top = heap.extractTop();
            Node next = top.next();
            if (next == null) {
                break;
            }
            heap.insert(next);
            Result newResult = heap.exportResult();
            if (newResult.range < ret.range) {
                ret = newResult;
            }
        }
        return ret;
    }
    
    private class MyHeap {
        private Node maxNode;
        private Heap<Node> heap = new Heap<MinRange.Node>(NODE_CMP);
        
        public void insert(Node node) {
            heap.insert(node);
            if (maxNode==null || maxNode.value()<node.value()) {
                maxNode = node;
            }
        }
        
        public int range() {
            return maxNode.value() - getTop().value();
        }
        
        public Result exportResult() {
            Result result = new Result();
            result.range = this.range();
            result.values = new int[heap.size()];
            for (int i=0; i<heap.size(); i++) {
                List<Integer> list = input.get(i);
                for (Node node:heap) {
                    if (node.list == list) {
                        result.values[i] = node.value();
                        break;
                    }
                }
            }
            return result;
        }
        
        public Node getTop() {
            return heap.getTop();
        }
        
        public Node extractTop() {
            return heap.extractTop();
        }
    }
    
    private static final Comparator<Node> NODE_CMP =
            new Comparator<MinRange.Node>() {
        public int compare(Node o1, Node o2) {
            return o1.value() - o2.value();
        };
    };
    
    private class Node {
        private final List<Integer> list;
        private final int index;
 
        public Node(List<Integer> list, int index) {
            super();
            this.list = list;
            this.index = index;
        }
        
        public int value() {
            return list.get(index);
        }
        
        public Node next() {
            if (index == list.size() - 1) {
                return null;
            }
            return new Node(list, index+1);
        }
    }
    
    public static class Result {
        private int[] values;
        private int range;
        @Override
        public String toString() {
            return "Result [values=" + Arrays.toString(values) + ", range="
                    + range + "]";
        }
    }
    
    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<List<Integer>>();
        
        List<Integer> list1 = new ArrayList<Integer>();
        input.add(list1);
        list1.add(1);
        list1.add(10);
        list1.add(15);
        list1.add(24);
        list1.add(26);
        
        List<Integer> list2 = new ArrayList<Integer>();
        input.add(list2);
        list2.add(0);
        list2.add(9);
        list2.add(12);
        list2.add(20);
        
        List<Integer> list3 = new ArrayList<Integer>();
        input.add(list3);
        list3.add(5);
        list3.add(18);
        list3.add(22);
        list3.add(30);
        
        Result r = new MinRange(input).exec();
        System.out.println(r);
    }
}