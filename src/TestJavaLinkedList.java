import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TestJavaLinkedList {

    public static void main(String[] args) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        double d = 4.1;
        double d1 = d/1000.0d;
        double d2 = d/1000.0f;
        float f = (float)(d/1000.0);
        float f2 = (float)(d/1000);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(f);
        System.out.println(f2);

        char[] chars = "s".toCharArray();
        String s = new String(chars);

        int[][] a  = new int[][] {{1, 3}};

        StringBuilder b;

        List<Integer> l = Collections.emptyList();
        List<Integer> ll = new ArrayList<>();
        l.addAll(ll);
        System.out.println(ll.size());
    }

    public void printClassName() {
        TestJavaLinkedList testJavaLinkedList = new TestJavaLinkedList();
    }


}
