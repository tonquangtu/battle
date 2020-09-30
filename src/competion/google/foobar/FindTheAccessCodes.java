package competion.google.foobar;

/**
 * In order to destroy Commander Lambda's LAMBCHOP doomsday device, you'll need access to it. But the only door
 * leading to the LAMBCHOP chamber is secured with a unique lock system whose number of passcodes changes daily.
 * Commander Lambda gets a report every day that includes the locks' access codes, but only she knows how to figure
 * out which of several lists contains the access codes. You need to find a way to determine which list contains the
 * access codes once you're ready to go in.
 *
 * Fortunately, now that you're Commander Lambda's personal assistant, she's confided to you that she made all the
 * access codes "lucky triples" in order to help her better find them in the lists. A "lucky triple" is a tuple (x,
 * y, z) where x divides y and y divides z, such as (1, 2, 4). With that information, you can figure out which list
 * contains the number of access codes that matches the number of locks on the door when you're ready to go in (for
 * example, if there's 5 passcodes, you'd need to find a list with 5 "lucky triple" access codes).
 *
 * Write a function solution(l) that takes a list of positive integers l and counts the number of "lucky triples" of
 * (li, lj, lk) where the list indices meet the requirement i < j < k.  The length of l is between 2 and 2000
 * inclusive.  The elements of l are between 1 and 999999 inclusive.  The answer fits within a signed 32-bit integer.
 * Some of the lists are purposely generated without any access codes to throw off spies, so if no triples are found,
 * return 0.
 *
 * For example, [1, 2, 3, 4, 5, 6] has the triples: [1, 2, 4], [1, 2, 6], [1, 3, 6], making the answer 3 total.
 * @author tu.tonquang
 */
public class FindTheAccessCodes {

    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 2, 3, 4, 5, 6}));
        System.out.println(solution(new int[] {1, 1, 1, 1}));
        System.out.println(solution(new int[] {1, 1, 1}));
        System.out.println(solution(new int[] {1, 1}));
    }

    public static int solution(int[] l) {
        int[] divideCounts = new int[l.length];
        for (int i = 0; i < l.length; i++) {
            for (int j = i + 1; j < l.length; j++) {
                if (l[j] % l[i] == 0) {
                    divideCounts[i] ++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < l.length; i++) {
            for (int j = i + 1; j < l.length; j++) {
                if (l[j] % l[i] == 0) {
                    count += divideCounts[j];
                }
            }
        }
        return count;

        //int count = 0;
        //for (int i = 0; i < l.length; i++) {
        //    for (int j = i + 1; j < l.length; j++) {
        //        for (int k = j + 1; k < l.length; k ++) {
        //            if (l[j] % l[i] == 0 && l[k] % l[j] == 0) {
        //                count ++;
        //            }
        //        }
        //    }
        //}
        //return count;
    }

}
