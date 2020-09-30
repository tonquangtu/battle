import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class CheapestFlightWithKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[n + 1][n + 2];
        Queue<CityLevel> queue = new ArrayDeque<>();
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        queue.add(new CityLevel(src, 0));
        while (!queue.isEmpty()) {
            CityLevel cityLevel = queue.poll();
            int departureCity = cityLevel.city;
            int nextLevel = cityLevel.level + 1;
            for (int[] flight : flights) {
                if (departureCity == flight[0]) {
                    int arrivalCity = flight[1];
                    int cost = dp[departureCity][nextLevel - 1] + flight[2];
                    if (dp[arrivalCity][nextLevel] == 0 || dp[arrivalCity][nextLevel] > cost) {
                        dp[arrivalCity][nextLevel] = cost;

                        if (nextLevel <= K && cost < minCost[arrivalCity]) {
                            queue.add(new CityLevel(arrivalCity, nextLevel));
                            minCost[arrivalCity] = cost;
                        }
                    }
                }
            }
        }

        int cheapest = Integer.MAX_VALUE;
        for (int i = 1; i <= K + 1; i++) {
            if (dp[dst][i] != 0 && dp[dst][i] < cheapest) {
                cheapest = dp[dst][i];
            }
        }
        return cheapest != Integer.MAX_VALUE ? cheapest : -1;
    }

    static class CityLevel {
        public int city;
        public int level;

        public CityLevel(int city, int level) {
            this.city = city;
            this.level = level;
        }
    }
}
