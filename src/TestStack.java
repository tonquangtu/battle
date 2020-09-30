import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class TestStack {
    public static void main(String[] args) {

        //Stack<String> stack = new Stack<>();
        //String s = "ss";
        //stack.push(String.valueOf(s.charAt(0)));

        List<String> list = Arrays.asList("tu", "ton", "quang");
        //List<String> collect = list.stream().filter(s -> s.equals("tu")).peek(t -> {
        //    System.out.println(t);
        //
        //
        //}).collect(Collectors.toList());


        list.stream().peek(s -> System.out.println(s)).forEach(s -> System.out.println("hhhhh"));
    }
}
