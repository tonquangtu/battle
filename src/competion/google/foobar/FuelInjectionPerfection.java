package competion.google.foobar;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Fuel Injection Perfection
 * =========================
 *
 * Commander Lambda has asked for your help to refine the automatic quantum antimatter fuel injection system for her
 * LAMBCHOP doomsday device. It's a great chance for you to get a closer look at the LAMBCHOP - and maybe sneak in a
 * bit of sabotage while you're at it - so you took the job gladly.
 *
 * Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP
 * each need to be fed fuel one pellet at a time. However, minions dump pellets in bulk into the fuel intake. You
 * need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time.
 *
 * The fuel control mechanisms have three operations:
 *
 * 1) Add one fuel pellet
 * 2) Remove one fuel pellet
 * 3) Divide the entire group of fuel pellets by 2 (due to the destructive energy released when a quantum antimatter
 * pellet is cut in half, the safety controls will only allow this to happen if there is an even number of pellets)
 *
 * Write a function called solution(n) which takes a positive integer as a string and returns the minimum number of
 * operations needed to transform the number of pellets to 1. The fuel intake control panel can only display a number
 * up to 309 digits long, so there won't ever be more pellets than you can express in that many digits.
 *
 * For example:
 * solution(4) returns 2: 4 -> 2 -> 1
 * solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1
 */
public class FuelInjectionPerfection {
    public static void main(String[] args) {
        System.out.println(solution("1"));
        System.out.println(solution("2"));
        System.out.println(solution("3"));
        System.out.println(solution("5"));
        System.out.println(solution("7"));
        System.out.println(solution("9"));
        System.out.println(solution("15"));
        System.out.println(solution("16"));
        System.out.println(solution("768"));
        System.out.println(solution("1027"));
        System.out.println(solution("1026"));
        //System.out.println(solution("7"));
        System.out.println("apple".compareTo("banana"));

        ArrayList<String> names = new ArrayList<>();
        names.add("tu");
        names.add("a");
        names.sort(Comparator.comparing(String::toString));
        System.out.println(names);
        int x, y;
        x = 0;
        y = 0;
        y = x ++;
        System.out.println(x + "," + y);
        System.out.println();
        for (int i = 0; i < 10; i = i++) {
            i +=1;
            System.out.println("hello");
            if ("a" instanceof String) {
                System.out.println("s");
            }
        }
    }

    public Exception test() {
        int[] a = {1, 2, 3};
        return new Exception();
    }

    public static int solution(String x) {
        int num = Integer.parseInt(x);
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= num; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i/2] + 1;
            } else {
                dp[i] = Math.min(dp[(i + 1)/2], dp[(i - 1)/2]) + 2;
            }
        }
        return dp[num];
    }

}
