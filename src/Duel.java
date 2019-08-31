import java.util.Arrays;
import java.util.List;

public class Duel {
    public static int minPower(List<Integer> p) {
        int minPower = 1;
        int currentPower = 1;
        int n = p.size();
        for(int i = 0; i < n; i++) {
            if (currentPower + p.get(i) < 1) {
                //boost more power
                int addingPower = 1 - (currentPower + p.get(i));
                currentPower = 1;
                minPower += addingPower;
            } else {
                currentPower += p.get(i);
            }
        }
        return minPower;
    }

    public static void main(String[] args) {
        System.out.println(minPower(Arrays.asList(-2, 3, 1, -5)));
        System.out.println(minPower(Arrays.asList(-3, -6, 15, 5)));
        System.out.println(minPower(Arrays.asList(3, 6, 15, 5)));
    }
}
