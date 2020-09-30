package test2;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ByteDanceTest2 {
    public static void main(String[] args) {
        //System.out.println("hello");
        //
        //String s = null;
        //if (s instanceof String) {
        //    System.out.println("string");
        //}
        //
        //Parent child1 = new Child1();
        //Parent child2 = new Child2();
        //
        //System.out.println(child1.getClass().getName());
        //System.out.println(child2.getClass().getName());

        String format = Instant.now().atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        System.out.println(format);

    }

    public static void test() {

    }
}

class Parent {

}

class Child1 extends Parent {

}

class Child2 extends Parent {

}
