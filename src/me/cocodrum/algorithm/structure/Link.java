/**
 * @(#)Link.java, Aug 18, 2013. 
 * 
 */
package me.cocodrum.algorithm.structure;

import java.util.Iterator;

/**
 * @author xuhongfeng
 *
 */
public class Link<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    
    public Node<T> pushHead(T value) {
        Node<T> node = new Node<T>();
        node.value = value;
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.last = node;
            head = node;
        }
        size++;
        return head;
    }
    
    public Node<T> pushTail(T value) {
        Node<T> node = new Node<T>();
        node.value = value;
        if (head == null) {
            head = tail = node;
        } else {
            node.last = tail;
            tail.next = node;
            tail = node;
        }
        size++;
        return tail;
    }
    
    public T popHead() {
        if (head == null) {
            return null;
        }
        size--;
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.last = null;
        }
        return value;
    }
    
    public T popTail() {
        if (head == null) {
            return null;
        }
        size--;
        T value = tail.value;
        tail = tail.last;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        return value;
    }
    
    public int size() {
        return size;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (T v:this) {
            if (!first) {
                sb.append(",");
            }
            first = false;
            sb.append(v);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkIterator();
    }
    
    private class LinkIterator implements Iterator<T> {
        private Node<T> p;
        
        private LinkIterator() {
            p = head;
        }

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public T next() {
            T v = p.value;
            p = p.next;
            return v;
        }

        @Override
        public void remove() {
            if (p == head) {
                head = p.next;
                return;
            }
            Node<T> q = head;
            while (q.next != p) {
                q = q.next;
            }
            q.next = p.next;
        }
    }

    private class Node<T> {
        private T value;
        private Node<T> last;
        private Node<T> next;
    }
}