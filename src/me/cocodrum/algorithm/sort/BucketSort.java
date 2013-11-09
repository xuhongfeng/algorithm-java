/**
 * @(#)BucketSort.java, Nov 3, 2013. 
 * 
 */
package me.cocodrum.algorithm.sort;

import java.util.Arrays;
import java.util.Iterator;

import me.cocodrum.algorithm.util.Utils;


/**
 * @author xuhongfeng
 *
 */
public class BucketSort extends AbsSort {

    @Override
    public void sort(int[] input, int start, int end) {
        if (end-start <= 6) {
            new InsertSort().sort(input, start, end);
            return;
        }
        
        int max = input[0];
        for (int i=start+1; i<=end; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        int size = end-start+1;
        int bucketSize = (int) Math.sqrt(size);
        Buckets buckets = new Buckets(bucketSize, max);
        
        for (int i=start; i<=end; i++) {
            int v = input[i];
            buckets.consume(v);
        }
        int i = start;
        for (int v:buckets) {
            input[i++] = v;
        }
    }
    
    private class Buckets implements Iterable<Integer> {
        private final int size;
        private final Bucket[] buckets;
        private final int max;
        
        public Buckets(int size, int max) {
            this.size = size;
            this.max = max;
            buckets = new Bucket[size];
            for (int i=0; i<size; i++) {
                buckets[i] = new Bucket();
            }
        }
        
        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int i = 0;
                private Iterator<Integer> it;

                @Override
                public boolean hasNext() {
                    while (i < size) {
                        if (it == null) {
                            it = buckets[i].iterator();
                        }
                        if (it.hasNext()) {
                            return true;
                        } else {
                            it = null;
                            i++;
                        }
                    }
                    return false;
                }

                @Override
                public Integer next() {
                    return it.next();
                }

                @Override
                public void remove() {
                }};
        }
        
        public void consume(int v) {
            getBucket(v).add(v);
        }
        
        private Bucket getBucket(int v) {
            int h = hash(v);
            return buckets[h];
        }
        
        private int hash(int v) {
            return (int)(1.0*(size-1)*v/max);
        }
    }

    private class Node {
        private int value;
        private Node next;
        
        public Node(int v) {
            this.value = v;
        }
    }
    
    private class Bucket implements Iterable<Integer> {
        private Node head;
        
        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private Node p = head;

                @Override
                public boolean hasNext() {
                    return p!=null;
                }

                @Override
                public Integer next() {
                    int v = p.value;
                    p = p.next;
                    return v;
                }

                @Override
                public void remove() {
                }
            };
        }
        
        public void add(int a) {
            Node node = new Node(a);
            if (head == null) {
                head = node;
            } else {
                if (head.value > a) {
                    node.next = head;
                    head = node;
                } else {
                    Node p = head;
                    while (p.next!=null && p.next.value<a) {
                        p = p.next;
                    }
                    if (p.next == null) {
                        p.next = node;
                    } else {
                        node.next = p.next;
                        p.next = node;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] a = Utils.generateIntArray(100);
        new BucketSort().sort(a);
        System.out.print(Arrays.toString(a));
    }
}
