package com.greenhand.leetcode;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(int[] arrays) {
        if (arrays.length == 0) {
            throw new RuntimeException("Array cannot be empty.");
        }
        this.val = arrays[0];
        ListNode pre = this;
        for (int i = 1; i < arrays.length; i++) {
            ListNode next= new ListNode(arrays[i]);
            pre.next = next;
            pre = next;
        }
    }
}
