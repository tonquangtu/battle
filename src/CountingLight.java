public class CountingLight {

    public static void main(String[] args) {
        var counter = new CountingLight();
//        var input = new int [] {2, 1, 3, 5, 4};
//        var input = new int [] {5, 4, 3, 2, 1, 6};
//        var input = new int [] {1, 2, 3, 4, 5, 6};
        var input = new int [] {3, 2, 1, 4, 5, 6};
        var result = counter.counting(input);
        System.out.println(result);
    }

    public int counting(int[] light) {
        boolean[] mark = new boolean[light.length + 1]; // to mark light i is on or off
        boolean[] visit = new boolean[light.length + 1]; // to mark light i is visit or not
        for (int i = 0; i <= light.length; i++) {
            mark[i] = false; // turn of the light
            visit[i] = false; // mark light i is not visit
        }

        int count = 0;
        for (int i = 0; i < light.length; i++) {
            visit[light[i]] = true;
            if (light[i] == 1 || mark[light[i] - 1]) {
                count += 1;
                turnOnLights(light, mark, visit, i);
            }
        }
        return count;
    }

    public void turnOnLights(int[] light, boolean[] mark, boolean[] visit, int index) {
        mark[light[index]] = true; // turn on this light and turn on lights after this light
        int prev = light[index] + 1;
        while (prev <= light.length) {
            if (!mark[prev] && visit[prev]) {
                mark[prev] = true;
                prev += 1;
            } else {
                break;
            }
        }
    }
}
