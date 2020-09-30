package competion.google.foobar;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * You've blown up the LAMBCHOP doomsday device and broken the bunnies out of Lambda's prison - and now you need to
 * escape from the space station as quickly and as orderly as possible! The bunnies have all gathered in various
 * locations throughout the station, and need to make their way towards the seemingly endless amount of escape pods
 * positioned in other parts of the station. You need to get the numerous bunnies through the various rooms to the
 * escape pods. Unfortunately, the corridors between the rooms can only fit so many bunnies at a time. What's more,
 * many of the corridors were resized to accommodate the LAMBCHOP, so they vary in how many bunnies can move through
 * them at a time.
 *
 * Given the starting room numbers of the groups of bunnies, the room numbers of the escape pods, and how many
 * bunnies can fit through at a time in each direction of every corridor in between, figure out how many bunnies can
 * safely make it to the escape pods at a time at peak.
 *
 * Write a function solution(entrances, exits, path) that takes an array of integers denoting where the groups of
 * gathered bunnies are, an array of integers denoting where the escape pods are located, and an array of an array of
 * integers of the corridors, returning the total number of bunnies that can get through at each time step as an int.
 * The entrances and exits are disjoint and thus will never overlap. The path element path[A][B] = C describes that
 * the corridor going from A to B can fit C bunnies at each time step.  There are at most 50 rooms connected by the
 * corridors and at most 2000000 bunnies that will fit at a time.
 *
 * For example, if you have:
 * entrances = [0, 1]
 * exits = [4, 5]
 * path = [
 *   [0, 0, 4, 6, 0, 0],  # Room 0: Bunnies
 *   [0, 0, 5, 2, 0, 0],  # Room 1: Bunnies
 *   [0, 0, 0, 0, 4, 4],  # Room 2: Intermediate room
 *   [0, 0, 0, 0, 6, 6],  # Room 3: Intermediate room
 *   [0, 0, 0, 0, 0, 0],  # Room 4: Escape pods
 *   [0, 0, 0, 0, 0, 0],  # Room 5: Escape pods
 * ]
 *
 * Then in each time step, the following might happen:
 * 0 sends 4/4 bunnies to 2 and 6/6 bunnies to 3
 * 1 sends 4/5 bunnies to 2 and 2/2 bunnies to 3
 * 2 sends 4/4 bunnies to 4 and 4/4 bunnies to 5
 * 3 sends 4/6 bunnies to 4 and 4/6 bunnies to 5
 *
 * So, in total, 16 bunnies could make it to the escape pods at 4 and 5 at each time step.  (Note that in this
 * example, room 3 could have sent any variation of 8 bunnies to 4 and 5, such as 2/6 and 6/6, but the final solution
 * remains the same.)
 *
 * Languages
 * =========
 *
 * To provide a Java solution, edit Solution.java
 * To provide a Python solution, edit solution.py
 */
public class EscapePods {
    public static final int MAX_THROUGHPUT = 200000;

    public static void main(String[] args) {
        System.out.println(solution(
            new int[] {0, 1},
            new int[] {4, 5},
            new int[][]{
                {0, 0, 4, 6, 0, 0},
                {0, 0, 5, 2, 0, 0},
                {0, 0, 0, 0, 4, 4},
                {0, 0, 0, 0, 6, 6},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}}));

        //System.out.println(solution(
        //    new int[] {0},
        //    new int[] {3},
        //    new int[][] {
        //        {0, 7, 0, 0},
        //        {0, 0, 6, 0},
        //        {0, 0, 0, 8},
        //        {9, 0, 0, 0}}
        //));
    }

    public static int solution(int[] entrances, int[] exits, int[][] path) {
        int[][] network = createNetwork(entrances, exits, path);
        int[][] throughput = new int[network.length][network.length];
        while (visitNetwork(network, throughput, 0, network.length - 1)) {

        }
        int totalThroughput = 0;
        for (int i = 1; i < network.length; i++) {
            totalThroughput += throughput[0][i];
        }
        return totalThroughput;
    }

    public static boolean visitNetwork(int[][] network, int[][] throughput, int start, int end) {
        boolean foundIncrementalThroughput = false;
        int[] path = new int[network.length];
        boolean[] visit = new boolean[network.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            visit[v] = true;
            if (v == end) {
                foundIncrementalThroughput = true;
                break;
            }
            for (int i = 0; i < network.length; i++) {
                if (v != i && !visit[i]) {
                    int capacity = getThroughputCapacity(v, i, network, throughput);
                    if (capacity > 0) {
                        queue.add(i);
                        path[i] = v;
                    }
                }
            }
        }

        if (foundIncrementalThroughput) {
            updateThroughput(network, throughput, path, start, end);
        }
        return foundIncrementalThroughput;
    }

    public static void updateThroughput(int[][] network, int[][] throughput, int[] path, int start, int end) {
        int capacity = Integer.MAX_VALUE;
        int curr = end;
        while (curr != start) {
            capacity = Math.min(capacity, getThroughputCapacity(path[curr], curr, network, throughput));
            curr = path[curr];
        }

        curr = end;
        while (curr != start) {
            if (network[path[curr]][curr] > 0) {
                throughput[path[curr]][curr] += capacity;
            } else {
                throughput[curr][path[curr]] -= capacity;
            }
            curr = path[curr];
        }
    }

    public static int getThroughputCapacity(int from, int to, int[][] network, int[][] throughput) {
        if (network[from][to] > 0) {
            return network[from][to] - throughput[from][to];
        }
        return throughput[to][from];
    }

    public static int[][] createNetwork(int[] entrances, int[] exits, int[][] path) {
        int[][] network = new int[path.length + 2][path.length + 2];
        int start = 0;
        int end = network.length - 1;
        for (int entrance: entrances) {
            network[start][entrance + 1] = MAX_THROUGHPUT;
        }
        for (int exit : exits) {
            network[exit + 1][end] = MAX_THROUGHPUT;
        }

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path.length; j++) {
                network[i + 1][j + 1] = path[i][j];
            }
        }
        return network;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("-------------------------");
    }

    public static void printPath(int[] path, int start, int end) {
        StringBuilder builder = new StringBuilder();
        int curr = end;
        while (curr != start) {
            builder.insert(0, "-" + curr);
            curr = path[curr];
        }
        builder.insert(0, start);
        System.out.println("Path: " + builder.toString());
    }
}
