package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TestMain {
    public static void main(String[] args) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        maxHeap.add(4);
        maxHeap.add(1);
        maxHeap.add(5);

        //
        //String s1 = "1";
        //System.out.println(s1.compareTo("2"));

        //PriorityQueue<String> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        //maxHeap.add("2");
        //maxHeap.add("1");
        //maxHeap.add("3");


        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
        //
        //String line = "1-2-3";
        //String[] split = line.split("-");
        //List<Integer> l = Arrays.asList(1, 2);
        //String collect = l.stream().map(String::valueOf).collect(Collectors.joining("-"));
        //System.out.println(collect);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("tu", "v1");
        map.put("tu2", "v2");
        map.get("tu");
        System.out.println(map.toString());

        SortedMap<Integer, String> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.put(1, "tu");
        sortedMap.put(2, "tu");
        sortedMap.put(6, "tu");
        sortedMap.put(0, "tu");
        sortedMap.put(3, "tu");

        for (var entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        Deque<Character> queue = new ArrayDeque<>();





    }

    public void test() {

    }
}
