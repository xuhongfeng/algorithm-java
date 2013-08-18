/**
 * @(#)LinkedQueue.java, Aug 18, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

/**
 * @author xuhongfeng
 *
 */
public class LinkedQueue<T> implements Queue<T> {
    private final Link<T> link = new Link<T>();

    @Override
    public int size() {
        return link.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void enQueue(T value) {
        link.pushTail(value);
    }

    @Override
    public T deQueue() {
        return link.popHead();
    }

}
