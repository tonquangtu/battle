import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * cho 1 danh sách N tasks, từ 1->N, mỗi task có thể depend vào 1 hay nhiều task khác. nhóm các task này lại thành một list các nhóm sao cho:
 * - các task trong một nhóm sẽ không depend lẫn nhau
 * - các task trong một nhóm sẽ không depend vào các task ở nhóm kế tiếp
 * - số nhóm là nhỏ nhất
 *
 * Example
 * testcases:
 *
 * Input:
 * ```[[4, 6], [3, 4], [2, 3], [3, 5], [1, 2], [1, 3], [5, 6], [7, 2]]```
 * diễn giải: 1 danh sách các cạnh. [4, 6] nghĩa là task số 6 depend vào task số 4
 *
 * Output:
 * ```[[1, 7], [2], [3], [4, 5], [6]]```
 * diễn giải, 1 danh sách các nhóm thoả mãn điều kiện đề bài
 */

// Time complexity is O(N+M) with M = number of edge and N is number of vertex
public class TaskScheduler2 {
    public ArrayList<ArrayList<Integer>> schedule(int N, int[][] edges) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (edges.length == 0) {
            return result;
        }

        // init graph (adjacent) and outDeg array for each vertex
        HashMap<Integer, ArrayList<Integer>> adjacent = new HashMap<>();
        int[] outDeg = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            outDeg[i] = 0;
            adjacent.put(i, new ArrayList<>(N));
        }

        // count number of out deg for each vertex in graph
        for (int i = 0; i < edges.length; i++) {
            adjacent.get(edges[i][0]).add(edges[i][1]);
            outDeg[edges[i][1]]++;
        }

        ArrayList<Integer> group = new ArrayList<>();
        ArrayList<Integer> tmpGroup;
        for (int i = 1; i <= N; i++) {
            if (outDeg[i] == 0) {
                group.add(i);
            }
        }

        // take all of vertex have out deg = 0 out of graph
        // and remove all edges of these vertex out of graph
        while (group.size() > 0) {
            tmpGroup = new ArrayList<>();
            result.add(group);
            for (Integer gv : group) {
                for (Integer v : adjacent.get(gv)) {
                    outDeg[v]--;
                    if (outDeg[v] == 0) {
                        tmpGroup.add(v);
                    }
                }
            }
            group = tmpGroup;
        }
        return result;
    }
}


class TestTaskScheduler2 {
    public static void main(String[] args) {
        var tester = new TestTaskScheduler2();

        // test 1
        var input = new int [][] {{4, 6}, {3, 4}, {2, 3}, {3, 5}, {1, 2}, {1, 3}, {5, 6}, {7, 2}};
        var N =  7;
        tester.test(N, input);
        System.out.println("------------------");

        // test 2
        var input2 = new int[][] {{4, 6}, {3, 4}, {2, 3}, {3, 5}, {1, 2}, {1, 3}, {5, 6}, {7, 2}, {4, 1}};
        var N2 = 7;
        tester.test(N2, input2);
        System.out.println("------------------");

        // test 3
        var input3 = new int[][] {{9, 5}, {8, 4}, {8, 5}, {7, 5}, {4, 1}, {4, 2}, {4, 3}, {5, 2}, {5, 3}};
        var N3 = 9;
        tester.test(N3, input3);
    }

    public void test(int N, int [][] input) {
        var taskScheduler = new TaskScheduler2();
        var result = taskScheduler.schedule(N, input);
        for (int i  = 0; i < result.size(); i++) {
            var group = result.get(i).stream().map(gv -> gv + "").collect(Collectors.joining(", "));
            System.out.println("{" + group + "}");
        }
    }
}
