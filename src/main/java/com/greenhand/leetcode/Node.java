package com.greenhand.leetcode;

import java.util.List;

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    public Node left;
    public Node right;
    public Node random;
    public List<Node> children;

    public Node() {

    }
    public Node(int val) {
        this.val = val;
    }
}