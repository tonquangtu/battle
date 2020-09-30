package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TekoTest {
    public static void main(String[] args) {
        System.out.println(minimizeBias(Arrays.asList(1, 3, 6, 6)));
        System.out.println(minimizeBias(Arrays.asList(1, 4, 2, 3)));
        System.out.println(minimizeBias(Arrays.asList(1, 5, 4, 2)));
    }

    public static int minimizeBias(List<Integer> ratings) {
        ratings.sort(Comparator.naturalOrder());
        int minBias = 0;
        for (int i = 1; i < ratings.size(); i += 2) {
            minBias += (ratings.get(i) - ratings.get(i - 1));
        }
        return minBias;
    }

    public static List<String> processLogs(List<String> logs, int maxSpan) {
        Map<Integer, DeltaTime> map = new HashMap<>();
        for (String log : logs) {
            String[] split = log.split(" ");
            int userId = Integer.parseInt(split[0]);
            int timestamp = Integer.parseInt(split[1]);

            DeltaTime deltaTime;
            if (!map.containsKey(userId)) {
                deltaTime = new DeltaTime();
            } else {
                deltaTime = map.get(userId);
            }
            if ("sign-in".equals(split[2])) {
                deltaTime.signIn = timestamp;
            } else {
                deltaTime.signOut = timestamp;
            }
            map.put(userId, deltaTime);
        }

        List<Integer> matchedUsers = new ArrayList<>();
        for (Entry<Integer, DeltaTime> entry : map.entrySet()) {
            if (entry.getValue().isLesserMaxSpan(maxSpan)) {
                matchedUsers.add(entry.getKey());
            }
        }
        matchedUsers.sort(Comparator.naturalOrder());
        List<String> res = new ArrayList<>();
        for (Integer matchedUser : matchedUsers) {
            res.add(String.valueOf(matchedUser));
        }
        return res;
    }

    static class DeltaTime {
        public Integer signIn;
        public Integer signOut;

        public boolean isLesserMaxSpan(int maxSpan) {
            if (signOut == null) {
                return false;
            }
            return (signOut - signIn) <= maxSpan;
        }
    }
    //
    //
    //
    //public static LogLine parseLog(String log) {
    //    String[] split = log.split(" ");
    //    int userId = Integer.parseInt(split[0]);
    //    int timestamp = Integer.parseInt(split[1]);
    //    int action = LogLine.SIGN_IN;
    //    if ("sign-out".equals(split[2])) {
    //        action = LogLine.SIGN_OUT;
    //    }
    //    return new LogLine(userId, timestamp, action);
    //}
    //
    //static class LogLine {
    //    final static int SIGN_IN = 1;
    //    final static int SIGN_OUT = 2;
    //    public int userId;
    //    public int timestamp;
    //    public int action;
    //
    //    public LogLine(int userId, int timestamp, int action) {
    //        this.userId = userId;
    //        this.timestamp = timestamp;
    //        this.action = action;
    //    }
    //}
}
