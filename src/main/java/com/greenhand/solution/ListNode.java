package com.greenhand.solution;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode index = this;
        while (index != null) {
            sb.append(index.val);
            sb.append(",");
            index = index.next;
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

}
