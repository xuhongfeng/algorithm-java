/**
 * @(#)Preorder.java, Sep 25, 2013. 
 * 
 */
package me.cocodrum.algorithm.tree;

import java.util.Stack;

import me.cocodrum.algorithm.tree.BinaryTree.Node;
import me.cocodrum.algorithm.tree.BinaryTree.TraverseCalback;

/**
 * @author xuhongfeng
 *
 */
public class Traverse {
    
    public void inorder(BinaryTree<Integer> tree, TraverseCalback<Integer> cbk) {
        this._inorder(tree.root, cbk);
    }
    
    public void inorder2(BinaryTree<Integer> tree, TraverseCalback<Integer> cbk) {
        if (tree.root == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
        Node<Integer> p = tree.root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        while (!stack.isEmpty()) {
            p = stack.pop();
            cbk.visit(p);
            p = p.right;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }
    }

    private void _inorder(Node<Integer> node, TraverseCalback<Integer> cbk) {
        if (node != null) {
            _inorder(node.left, cbk);
            cbk.visit(node);
            _inorder(node.right, cbk);
        }
    }
    
    public void preorder(BinaryTree<Integer> tree, TraverseCalback<Integer> cbk) {
        this._preorder(tree.root, cbk);
    }
    
    public void preorder2(BinaryTree<Integer> tree, TraverseCalback<Integer> cbk) {
        if (tree.root == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
        stack.add(tree.root);
        while (!stack.isEmpty()) {
            Node<Integer> node = stack.pop();
            cbk.visit(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    private void _preorder(Node<Integer> node, TraverseCalback<Integer> cbk) {
        if (node != null) {
            cbk.visit(node);
            _preorder(node.left, cbk);
            _preorder(node.right, cbk);
        }
    }
    
    public void postorder(BinaryTree<Integer> tree, TraverseCalback<Integer> cbk) {
        this._postorder(tree.root, cbk);
    }

    private void _postorder(Node<Integer> node, TraverseCalback<Integer> cbk) {
        if (node != null) {
            _postorder(node.left, cbk);
            _postorder(node.right, cbk);
            cbk.visit(node);
        }
    }
    
    public void postorder2(BinaryTree<Integer> tree, TraverseCalback<Integer> cbk) {
        if (tree.root == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
        Node<Integer> prev = null;
        stack.push(tree.root);
        while (!stack.isEmpty()) {
            Node<Integer> node = stack.pop();
            if (prev==null || node.parent==prev) {
                if (node.left != null) {
                    stack.push(node);
                    stack.push(node.left);
                } else if (node.right!= null) {
                    stack.push(node);
                    stack.push(node.right);
                } else {
                    cbk.visit(node);
                }
            } else if (node.left == prev) {
                if (node.right!= null) {
                    stack.push(node);
                    stack.push(node.right);
                } else {
                    cbk.visit(node);
                }
            } else {
                cbk.visit(node);
            }
            prev = node;
        }
    }
    
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.setRoot(0);
        tree.insert(tree.root, 1, true);
        tree.insert(tree.root, 4, false);
        tree.insert(tree.root.left, 2, true);
        tree.insert(tree.root.left, 3, false);
        tree.insert(tree.root.right, 5, false);
        TraverseCalback<Integer> cbk = new TraverseCalback<Integer>() {

            @Override
            public void visit(Node<Integer> node) {
                System.out.print(node.value + "  ");
            }
        };
        
        new Traverse().preorder(tree, cbk);
        System.out.println();
        new Traverse().preorder2(tree, cbk);
        System.out.println();
        new Traverse().inorder(tree, cbk);
        System.out.println();
        new Traverse().inorder2(tree, cbk);
        System.out.println();
        new Traverse().postorder(tree, cbk);
        System.out.println();
        new Traverse().postorder2(tree, cbk);
        System.out.println();
    }
}
