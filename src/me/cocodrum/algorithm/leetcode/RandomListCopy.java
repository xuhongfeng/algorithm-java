package me.cocodrum.algorithm.leetcode;

public class RandomListCopy {
    public RandomListNode copyRandomList(RandomListNode head) {
        // Note: The Solution object is instantiated only once and is reused by
        // each test case.
       if (head == null) return null;
        
        RandomListNode p = head;
        while (p != null) {
            RandomListNode q = new RandomListNode(p.label);
            q.next = p.next;
            p.next = q;
            p = q.next;
        }
        
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            } else {
                p.next.random = null;
            }
            p=p.next.next;
        }
        
        p = head;
        RandomListNode ret = head.next;
        RandomListNode q = ret;
        while (p != null) {
            p.next = q.next;
            if (q.next != null) {
                q.next = q.next.next;
            } else {
                q.next = null;
            }
            
            p = p.next;
            q = q.next;
        }
        
        return ret;
    }

    public static class RandomListNode {
        int label;

        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(" + label);
            if (next != null) {
                sb.append("," + next.label);
            }
            if (random != null) {
                sb.append("," + random.label);
            }
            sb.append(")  ");
            
            if (next != null) {
                sb.append(next);
            }
            
            return sb.toString();
        }
        
        
    };

    
    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(-1);
        System.out.println(head);
        RandomListNode copy = new RandomListCopy().copyRandomList(head);
        System.out.println(copy);
    }
}
