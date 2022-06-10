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

    static ListNode buildFromArray(int[] arrays) {
        ListNode preHead = new ListNode(0);
        ListNode pre = preHead;
        for (int array : arrays) {
            ListNode cur = new ListNode(array);
            pre.next = cur;
            pre = cur;
        }
        return preHead.next;
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
