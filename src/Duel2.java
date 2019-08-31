import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Duel2 {

    public int minPower(List<Integer> p) {
        int minPower = 1;
        int currPower = 1;
        for (int i = 0; i < p.size(); i++) {
            int power = p.get(i);
            if (currPower + power < 1) {
                minPower += 1 - (currPower + power);
                currPower = 1;
            } else {
                currPower += power;
            }
        }
        return minPower;
    }

    public static void main(String[] args) {
        var duel = new Duel2();
        System.out.println(duel.minPower(Arrays.asList(-2, 3, 1, -5)));
        System.out.println(duel.minPower(Arrays.asList(-3, -6, 15, 5)));
        System.out.println(duel.minPower(Arrays.asList(3, 6, 15, 5)));



    }
}
