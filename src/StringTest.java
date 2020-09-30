import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringTest {
    public static void main(String[] args) {
        String test = "hello";
        System.out.println(test.contains("lfl"));
        int a = 8;
        char c = (char)(a + '0');
        System.out.println("anh tu: "+ (char)c);

        int i = 0;
        int j = 0;
        for (; i < 2; i++) {
            for (; j < 2; j++) {
                System.out.println(i + "," + j) ;
            }
        }

        Set<List<Integer>> set = new HashSet<>();
        set.toArray();
        int[] res = new int[] {1, 3};
        StringBuilder builder = new StringBuilder();
        for (int k = res.length - 1; k>= 0; k--) {
            builder.append(res[k]);
        }

        String ss = builder.toString();
    }
}
