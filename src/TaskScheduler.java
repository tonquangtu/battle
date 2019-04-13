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

public class TaskScheduler {
    public ArrayList<ArrayList<Integer>> taskScheduler(int N, int[][] edges) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (edges.length == 0) {
            return result;
        }

        HashMap<Integer, ArrayList<Integer>> adjacent = new HashMap<>();
        int[] outDeg = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            outDeg[i] = 0;
            adjacent.put(i, new ArrayList<>(N + 1));
        }
        // count number of out deg for each vertex in graph
        for (int i = 0; i < edges.length; i++) {
            adjacent.get(edges[i][0]).add(edges[i][1]);
            outDeg[edges[i][1]] ++;
        }

        // take all of vertex have out deg = 0 out of graph
        // and remove all edges of these vertex out of graph
        ArrayList<Integer> group;
        while (true) {
            group = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (outDeg[i] == 0) {
                    group.add(i);
                    outDeg[i] = -1;
                }
            }
            if (group.size() == 0) {
                break;
            }
            // remove all edge of these vertex in this group from graph
            for (Integer gv : group) {
                for (Integer v : adjacent.get(gv)) {
                    outDeg[v]--;
                }
            }
            result.add(group);
        }

        return result;
    }
}

class TestTaskScheduler {
    public static void main(String[] args) {
        var tester = new TestTaskScheduler();

        // test 1
        var input = new int [][] {{4, 6}, {3, 4}, {2, 3}, {3, 5}, {1, 2}, {1, 3}, {5, 6}, {7, 2}};
        var N =  7;
        tester.test(N, input);
        System.out.println("------------------");

        // test 2
        var input2 = new int[][] {{4, 6}, {3, 4}, {2, 3}, {3, 5}, {1, 2}, {1, 3}, {5, 6}, {7, 2}, {4, 1}};
        var N2 = 7;
        tester.test(N2, input2);
    }

    public void test(int N, int [][] input) {
        var taskScheduler = new TaskScheduler();
        var result = taskScheduler.taskScheduler(N, input);
        for (int i  = 0; i < result.size(); i++) {
            var group = result.get(i).stream().map(gv -> gv + "").collect(Collectors.joining(", "));
            System.out.println("{" + group + "}");
        }
    }
}
