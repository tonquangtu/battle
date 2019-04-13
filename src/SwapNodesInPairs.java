/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */

//  Definition for singly-linked list.

public class SwapNodesInPairs {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        head = head.next;

        while (curr != null && (next = curr.next) != null) {
            curr.next = next.next;
            next.next = curr;
            if (prev != null) {
                prev.next = next;
            }
            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    // for testing
    public ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode prev = head;
        ListNode next;
        for (int i = 1; i < arr.length; i++) {
            next = new ListNode(arr[i]);
            prev.next = next;
            prev = next;
        }
        return head;
    }

    // for testing
    public void printList(ListNode head) {
        if (head == null) {
            System.out.println("List empty");
            return;
        }
        ListNode curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }

}

class TestSwapNodesInParis {
    public static void main(String[] args) {
        var tester = new SwapNodesInPairs();
        int[] input = new int[] {1, 2, 3, 4, 5, 6};
        var head = tester.createList(input);
        tester.printList(head);
        System.out.println("--------------");
        var newHead = tester.swapPairs(head);
        tester.printList(newHead);
    }
}
