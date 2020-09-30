import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TestJavaStream {

    public static void main(String[] args) {
        TestJavaStream streamTester = new TestJavaStream();
        streamTester.testFindFirst();
        streamTester.testFindAny();
        streamTester.testConcat();
    }

    public void testFindFirst() {
        System.out.println("Test find first");
        int n = 10;
        List<String> l1 = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            l1.add("" + i);
        }
        Optional<Integer> found = l1
                .stream().map(item -> {
                    System.out.println("item: " + item);
                    return Integer.parseInt(item);
                })
                .filter(num -> num >= n + 1)
                .findFirst();
        System.out.println("Found: " + found.orElse(-1));
    }

    public void testFindAny() {
        System.out.println("Test find any");
        int n = 10;
        List<String> l1 = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            l1.add("" + i);
        }
        Optional<Integer> found = l1
                .stream().map(item -> {
                    System.out.println("item: " + item);
                    return Integer.parseInt(item);
                })
                .filter(num -> num >= 8)
                .findAny();
        System.out.println("Found: " + found.orElse(-1));
    }

    public void testConcat() {
        System.out.println("Test concat");
        int n = 10;
        List<String> l1 = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            l1.add("" + i);
        }
        Optional<Integer> found = Stream.concat(
                l1.stream()
                        .map(item -> {
                            System.out.println("item: " + item);
                            return Integer.parseInt(item);
                        })
                        .filter(num -> num <= 5),
                l1.stream() //
                        .map(item -> {
                            System.out.println("item: " + item);
                            return Integer.parseInt(item);
                        })
                        .filter(num -> num > 5)
        ).filter(num -> num % 8 == 0).findFirst();
        System.out.println("Found: " + found.orElse(-1));
    }
}
