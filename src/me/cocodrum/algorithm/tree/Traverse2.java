/**
 * @(#)Traverse2.java, Nov 1, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

/**
 * @author xuhongfeng
 *
 */
public class Traverse2 {
    
    public void postOrder(Node tree) {
        Node pre = null;
        Node p = tree;
        
        while (p != null) {
            if (pre==null || p.parent==pre) {
                if (p.left != null) {
                    pre = p;
                    p = p.left;
                } else if (p.right != null) {
                    pre = p;
                    p = p.right;
                } else {
                    visit(p);
                    pre = p;
                    p = p.parent;
                }
            } else if (p.left == pre) {
                if (p.right != null) {
                    pre = p;
                    p = p.right;
                } else {
                    visit(p);
                    pre = p;
                    p = p.parent;
                }
            } else {
                visit(p);
                pre = p;
                p = p.parent;
            }
        }
    }
    
    public void inOrder(Node tree) {
        Node pre = null;
        Node p = tree;
        
        while (p != null) {
            if (pre==null || p.parent==pre) {
                if (p.left != null) {
                    pre = p;
                    p = p.left;
                } else {
                    visit(p);
                    if (p.right != null) {
                        pre = p;
                        p = p.right;
                    } else {
                        pre = p;
                        p = p.parent;
                    }
                }
            } else if (p.left == pre) {
                visit(p);
                if (p.right != null) {
                    pre = p;
                    p = p.right;
                } else {
                    pre = p;
                    p = p.parent;
                }
            } else {
                pre = p;
                p = p.parent;
            }
        }
    }
    
    public void rInOrder(Node tree) {
        if (tree == null) return;
        rInOrder(tree.left);
        visit(tree);
        rInOrder(tree.right);
    }
    
    private void visit(Node node) {
        System.out.print(node.value + "  ");
    }
    
    public void rPostOrder(Node tree) {
        if (tree == null) return;
        
        rPostOrder(tree.left);
        rPostOrder(tree.right);
        visit(tree);
    }
    
    public void preOrder(Node tree) {
        Node pre = null;
        Node p = tree;
        
        while (p != null) {
            if (pre==null || p.parent==pre) {
                visit(p);
                if (p.left != null) {
                    pre = p;
                    p = p.left;
                } else if (p.right != null) {
                    pre = p;
                    p = p.right;
                } else {
                    pre = p;
                    p = p.parent;
                }
            } else if (p.left == pre) {
                if (p.right != null) {
                    pre = p;
                    p = p.right;
                } else {
                    pre = p;
                    p = p.parent;
                }
            } else {
                pre = p;
                p = p.parent;
            }
        }
    }
    
    public void rPreorder(Node tree) {
        if (tree == null) return;
        visit(tree);
        rPreorder(tree.left);
        rPreorder(tree.right);
    }

    private static class Node {
        private int value;
        Node left, right, parent;
        
        public Node(int v) {
            this.value = v;
        }
    }
    
    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.left.parent = tree;
        
        tree.left.right = new Node(3);
        tree.left.right.parent = tree.left;
        
        tree.right = new Node(4);
        tree.right.parent = tree;
        
        tree.right.right = new Node(5);
        tree.right.right.parent = tree.right;
        
        tree.left.left = new Node(6);
        tree.left.left.parent = tree.left;
        
        new Traverse2().preOrder(tree);
        System.out.println();
        new Traverse2().rPreorder(tree);
    }

}
