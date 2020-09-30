package competion.google.foobar;

import java.util.Arrays;

/**
 * You've been assigned the onerous task of elevator maintenance - ugh! It wouldn't be so bad, except that all the
 * elevator documentation has been lying in a disorganized pile at the bottom of a filing cabinet for years, and you
 * don't even know what elevator version numbers you'll be working on.
 *
 * Elevator versions are represented by a series of numbers, divided up into major, minor and revision integers. New
 * versions of an elevator increase the major number, e.g. 1, 2, 3, and so on. When new features are added to an
 * elevator without being a complete new version, a second number named "minor" can be used to represent those new
 * additions, e.g. 1.0, 1.1, 1.2, etc. Small fixes or maintenance work can be represented by a third number named
 * "revision", e.g. 1.1.1, 1.1.2, 1.2.0, and so on. The number zero can be used as a major for pre-release versions
 * of elevators, e.g. 0.1, 0.5, 0.9.2, etc (Commander Lambda is careful to always beta test her new technology, with
 * her loyal henchmen as subjects!).
 *
 * Given a list of elevator versions represented as strings, write a function solution(l) that returns the same list
 * sorted in ascending order by major, minor, and revision number so that you can identify the current elevator
 * version. The versions in list l will always contain major numbers, but minor and revision numbers are optional. If
 * the version contains a revision number, then it will also have a minor number.
 *
 * For example, given the list l as ["1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"], the function solution(l) would
 * return the list ["1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"]. If two or more versions are equivalent but one
 * version contains more numbers than the others, then these versions must be sorted ascending based on how many
 * numbers they have, e.g ["1", "1.0", "1.0.0"]. The number of elements in the list l will be at least 1 and will not
 * exceed 100.
 */
public class ElevatorMaintenance {
    public static void main(String[] args) {
        //System.out.println(Arrays.toString("1.2".split("\\.")));
        //System.out.println(Arrays.toString("1".split("\\.")));
        //System.out.println(Arrays.toString("1.0.1".split("\\.")));
        //0.1, 1.1.1, 1.2, 1.2.1, 1.11, 2, 2.0, 2.0.0
        System.out.println(Arrays.toString(solution(new String[] {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})));
        System.out.println(Arrays.toString(solution(new String[] {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"})));
    }

    public static String[] solution(String[] l) {
        Arrays.sort(l, (item1, item2) -> {
            String[] split1 = item1.split("\\.");
            String[] split2 = item2.split("\\.");

            int majorCompare = compareStr(split1[0], split2[0]);
            if (majorCompare != 0) {
                return majorCompare;
            } else {
                if (split1.length >= 2 && split2.length >= 2) {
                    int minorCompare = compareStr(split1[1], split2[1]);
                    if (minorCompare != 0) {
                        return minorCompare;
                    } else {
                        if (split1.length == 3 && split2.length == 3) {
                            return compareStr(split1[2], split2[2]);
                        } else {
                            return split1.length - split2.length;
                        }
                    }
                } else {
                    return split1.length - split2.length;
                }
            }
        });
        return l;
    }

    public static int compareStr(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return s1.compareTo(s2);
        } else if (s1.length() > s2.length()) {
            return 1;
        } else {
            return -1;
        }
    }
}
