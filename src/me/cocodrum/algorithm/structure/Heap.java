/**
 * @(#)Heap.java, Aug 28, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuhongfeng
 *
 */
public class Heap<T> implements Iterable<T> {
    private final List<T> values = new ArrayList<T>();
    private final Comparator<T> cmp;
    private int size=0;

    public Heap(Comparator<T> cmp) {
        super();
        this.cmp = cmp;
    }
    
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < size;
            }

            @Override
            public T next() {
                return values.get(idx++);
            }

            @Override
            public void remove() {
            }};
        return it;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public void insert(T value) {
        if (values.size() == size) {
            values.add(value);
        } else {
            values.set(size, value);
        }
        size++;
        
        heapifyUp(parent(size-1));
    }
    
    public T getTop() {
        return size==0 ? null : values.get(0);
    }
    
    public T extractTop() {
        if (size == 0) {
            return null;
        }
        swap(0, size-1);
        size--;
        heapifyDown(0);
        return values.get(size);
    }
    
    public void update(T value) {
        int i = indexOf(value);
        if (i<0 || i>=size) {
            return;
        }
        
        if (i > 0) {
            int parent = parent(i);
            if (compare(parent, i) > 0) {
                heapifyUp(parent);
                return;
            }
        }
        int left = left(i);
        int right = right(i);
        if (left<size && compare(left, i) < 0
                ||right<size && compare(right, i) < 0) {
            heapifyDown(i);
        }
    }
    
    private int compare(int i, int j) {
        return cmp.compare(values.get(i), values.get(j));
    }
    
    private int indexOf(T value) {
        return this.values.indexOf(value);
    }
    
    private int parent(int i) {
        return (i-1)/2;
    }
    
    private int right(int i) {
        return 2*i + 2;
    }
    
    private int left(int i) {
        return 2*i + 1;
    }
    
    private void heapifyDown(int i) {
        int min = innerHeapify(i);
        if (min != i) {
            heapifyDown(min);
        }
    }
    
    private void heapifyUp(int i) {
        int min = innerHeapify(i);
        if (min != i) {
            heapifyUp(parent(i));
        }
    }
    
    private int innerHeapify(int i) {
        int min = i;
        int left = left(i);
        if (left<size && cmp.compare(values.get(left), values.get(min)) < 0) {
            min = left;
        }
        int right = right(i);
        if (right<size && cmp.compare(values.get(right), values.get(min)) < 0) {
            min = right;
        }
        if (min != i) {
            swap(min, i);
        }
        return min;
    }
    
    private void swap(int i, int j) {
        T t = values.get(i);
        values.set(i, values.get(j));
        values.set(j, t);
    }
    
    public static class MinIntegerHeap extends Heap<Integer> {
        
        public MinIntegerHeap() {
            super(CMP);
        }

        private static final Comparator<Integer> CMP = new Comparator<Integer>() {
            
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
    }
}