/**
 * @(#)ArrayQueue.java, Aug 13, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

/**
 * @author xuhongfeng
 *
 */
public class ArrayQueue<T> implements Queue<T> {
    private final Object[] array;
    private int size = 0;
    private int head = 0;
    
    public ArrayQueue(int capacity) {
        super();
        array = new Object[capacity];
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    @Override
    public void enQueue(T value) {
        if (size == array.length) {
            throw new IllegalStateException();
        }
        int idx = (head + size) % array.length;
        array[idx] = value;
        size++;
    }
    
    @Override
    public T deQueue() {
        if (isEmpty()) {
            return null;
        }
        int idx = head;
        head = (head+1) % array.length;
        size--;
        return (T) array[idx];
    }

}
