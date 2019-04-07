
//* Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


public class AddTwoNumbers {
    private ListNode head = null;
    private ListNode curr = null;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int mem = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + mem;
            if (sum >= 10) {
                mem = 1;
                sum = sum - 10;
            } else {
                mem = 0;
            }
            addNode(sum);
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode l = l1 != null ? l1 : l2;
        while (l != null) {
            int sum = l.val + mem;
            if (sum >= 10) {
                mem = 1;
                sum = sum - 10;
            } else {
                mem = 0;
            }
            addNode(sum);
            l = l.next;
        }

        if (mem > 0) {
            addNode(mem);
        }
        return head;

    }

    public void addNode(int val) {
        ListNode temp = new ListNode(val);
        if (this.head == null) {
            this.head = temp;
        } else if (this.head.next == null) {
            this.head.next = temp;
        } else {
            this.curr.next = temp;
        }
        this.curr = temp;
    }
}

class Test {
    public static void main(String[] args) {
        var adder = new AddTwoNumbers();
        var arr1 = new int [] {9, 8};
        var l1 = createList(arr1);

        var arr2 = new int [] {1};
        var l2 = createList(arr2);

        printList(l1);
        printList(l2);

        var l3 = adder.addTwoNumbers(l1, l2);
        printList(l3);
    }

    public static ListNode createList(int [] arrs) {
        if (arrs.length == 0) {
            return null;
        }
        var head = new ListNode(arrs[0]);
        if (arrs.length == 1) {
            return head;
        }
        head.next = new ListNode(arrs[1]);
        var temp = head.next;
        for (int i = 2; i < arrs.length; i++) {
            temp.next = new ListNode(arrs[i]);
            temp = temp.next;
        }

        return head;
    }

    public static void printList(ListNode head) {
        System.out.println("--------------------------");
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}