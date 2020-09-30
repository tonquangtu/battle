import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaThreadLocal {

    public static void main(String[] args) {
        JavaThreadLocal javaThreadLocal = new JavaThreadLocal();
        javaThreadLocal.testThreadLocal();

        Instant now = Instant.now();

        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.UTC);
        String format = offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+00:00"));
        System.out.println(format);
        //OffsetDateTime offsetDateTime1 = now.atOffset(ZoneOffset.ofHours(1));
        ////System.out.println(offsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        ////System.out.println(offsetDateTime1.format(DateTimeFormatter.ISO_DATE_TIME));
        //
        ////System.out.println(offsetDateTime.toZonedDateTime().toString());
        ////offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+"));
        //
        //
        //DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss+00:00")
        //    .withZone(ZoneOffset.UTC);
        //
        //System.out.println(DATE_TIME_FORMATTER.format(now));

        //testStream();

       parseDoubleOrDef(null, null);
    }

    public void testThreadLocal() {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("A thread local value");
        String threadLocalValue = (String) threadLocal.get();
        System.out.println(threadLocalValue);
    }

    public static void testStream() {
        List<String> l1 = Arrays.asList("tu", "tu1", "tu2", "tu6");
        List<String> l2 = Arrays.asList("tu", "tu1", "tu3", "tu4", "tu5");

        List<String> collect = l1.stream().filter(i1 -> {
            return l2.stream().noneMatch(i2 -> i2.equals(i1));
        }).collect(Collectors.toList());

        System.out.println(collect.toString());

    }

    public static Double parseDoubleOrDef(String s, Double def) {
        try {
            return s == null ? null : Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }


}
