import java.util.Arrays;
import java.util.Optional;

public class TestOptional {
    public static void main(String[] args) {
        Optional.of("tu").filter(s -> s.startsWith("t")).ifPresent(s -> {
            System.out.println(s);
        });
        Optional.of("tu").filter(s -> s.startsWith("u")).ifPresent(s -> {
            System.out.println(s);
        });

        Arrays.asList("1", "2").stream().map(s -> {
            System.out.println(s);
            return s;
        });
    }
}
