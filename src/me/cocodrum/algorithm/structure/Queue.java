/**
 * @(#)Queue.java, Aug 13, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

/**
 * @author xuhongfeng
 *
 */
public interface Queue<T> extends Collection {

    public void enQueue(T value);
    public T deQueue();
}
