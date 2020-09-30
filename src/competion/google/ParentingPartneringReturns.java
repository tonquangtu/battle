package competion.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/000000000020bdf9
 */
public class ParentingPartneringReturns {

    public static void main(String[] args) {
        ParentingPartneringReturns solution = new ParentingPartneringReturns();
        //solution.test(Arrays.asList(new Activity(360, 480, 0), new Activity(420, 540, 1), new Activity(600, 660, 2)), "CJC");
        //solution.test(Arrays.asList(new Activity(0, 1440, 0), new Activity(1, 3, 1), new Activity(2, 4, 2)), "IMPOSSIBLE");
        //solution.test(Arrays.asList(
        //    new Activity(99, 150, 0),
        //    new Activity(1, 100, 1),
        //    new Activity(100, 301, 2),
        //    new Activity(2, 5, 3),
        //    new Activity(150, 250, 4)),
        //    "JCCJJ");
        //
        //solution.test(Arrays.asList(
        //    new Activity(0, 720, 0),
        //    new Activity(720, 1440, 1)),
        //    "CC");
        solution.scanAndSolve();

    }

    public void test(List<Activity> activities, String expect) {
        String output = solve(activities);
        System.out.println("--------------------------------------");
        System.out.println(String.format("input: %s, ", activities));
        System.out.println(String.format("output: %s, expect: %s", output, expect));
        if (!output.equals(expect)) {
            throw new RuntimeException(String.format("Fail at test case: %s", activities));
        }
    }

    public void scanAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int testSize = Integer.parseInt(scanner.nextLine());

        for (int tc = 1; tc <= testSize; tc++) {
            int actSize = scanner.nextInt();
            List<Activity> acts = new ArrayList<>(actSize);
            for (int i = 1; i <= actSize; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                acts.add(new Activity(start, end, i));
            }
            String res = solve(acts);
            System.out.println(String.format("Case #%s: %s", tc, res));
        }
    }


    public String solve(List<Activity> activities) {
        activities.sort((o1, o2) -> o1.start - o2.start);
        int currentEndCameron = 0;
        int currentEndJamie = 0;
        for (Activity act : activities) {
            if (act.start >= currentEndCameron) {
                act.assignToCameron();
                currentEndCameron = act.end;
            } else if (act.start >= currentEndJamie) {
                act.assignToJamie();
                currentEndJamie = act.end;
            } else {
                return Activity.IMPOSSIBLE;
            }
        }
        activities.sort(((o1, o2) -> o1.position - o2.position));
        StringBuilder schedule = new StringBuilder();
        activities.forEach(act -> schedule.append(act.assigned));
        return schedule.toString();
    }

    public static class Activity {
        public static char CAMERON = 'C';
        public static char JAMIE = 'J';
        public static String IMPOSSIBLE = "IMPOSSIBLE";

        int start;
        int end;
        int position;
        char assigned;

        public Activity(int start, int end, int position) {
            this.start = start;
            this.end = end;
            this.position = position;
        }

        public void assignToCameron() {
            this.assigned = CAMERON;
        }

        public void assignToJamie() {
            this.assigned = JAMIE;
        }

        @Override
        public String toString() {
            return "Activity{" +
                "start=" + start +
                ", end=" + end +
                ", position=" + position +
                ", assigned=" + assigned +
                '}';
        }
    }
}
