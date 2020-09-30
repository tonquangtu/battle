import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class ParseStrToDate {

    public static final int DELIVERY_TIME_FRAME_FROM = 8;
    public static final int DELIVERY_TIME_FRAME_TO = 16;
    public static final int[] DELIVERY_TIME_FRAME_OFF_DAYS = new int[] {0};
    public static final String TIMEZONE_ID = "Asia/Ho_Chi_Minh";


    public static void main(String[] args) {
        String timestamp = "2019-02-01 06:01:01";
        parseTimestamp(timestamp);
        parseTimestamp2(timestamp);
        generateTime();
        toStrInstant();

        System.out.println("----------RFC---------");
        //String rfc1 = "2019-02-01T06:01:01Z";
        String rfc2 = "2019-02-01T06:01:01+03:00";
        String rfc3 = "2019-02-01 06:01:01";
        //parseTimestampRFC3339(rfc1);
        parseTimestampRFC3339(rfc2);
        //parseTimestampRFC3339(rfc3);
        System.out.println("------------------");
        adjustTime();
        System.out.println("----------------");
        calculateIdleTime();

    }

    public static void parseTimestamp(String timestamp) {
        var time = LocalDateTime.parse(
            timestamp ,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m:ss")
        ).atZone(
                ZoneId.of("Asia/Jakarta" )
        ).toInstant().toString();
        System.out.println(time);
    }

    public static void parseTimestamp2(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        TemporalAccessor temporalAccessor = formatter.parse(timestamp);
        LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Jakarta"));
        Instant result = Instant.from(zonedDateTime);
        System.out.println(result.toString());
    }

    public static void generateTime() {
        System.out.println("--------------");
        ZonedDateTime zonedNow = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Jakarta"));
        Instant now = Instant.from(zonedNow);
        System.out.println(now.toString());
        System.out.println(Instant.now().toString());
        System.out.println(new Date().toInstant().toString());
        System.out.println("------------");
        System.out.println(LocalDateTime.now().toString());
    }

    public static void toStrInstant()  {
        Instant now = Instant.now();
        System.out.println(now.toString());
    }

    public static void parseTimestampRFC3339(String timestamp) {
        try {
            String time = OffsetDateTime
                .parse(timestamp , DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .toInstant()
                .atZone(ZoneId.of("Asia/Jakarta"))
                .toString();
            System.out.println(time);
            //
            //Instant res = LocalDateTime
            //    .parse(timestamp , DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
            //    .atZone(ZoneId.of("Asia/Jakarta"))
            //    .toInstant();
            //System.out.println(res.toString());
        } catch (DateTimeParseException e) {
            throw new RuntimeException(String.format("Timestamp: %s is not in yyyy-MM-dd'T'HH:mm:ssXXX format", timestamp));
        }
    }

    public static void adjustTime() {
        LocalDateTime localDateTime = Instant.now().atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDateTime();
        LocalDateTime another = localDateTime.toLocalDate().atTime(8, 0);
        System.out.println(localDateTime.toString());
        System.out.println(another.toString());
        System.out.println(Instant.now().toString());


        ZonedDateTime zonedDateTime = another.atZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        long value = zonedDateTime.toEpochSecond();

    }

    public static Optional<Long> calculateIdleTime() {
        LocalDateTime currTime = Instant.now()
            .atZone(ZoneId.of(TIMEZONE_ID)).toLocalDateTime();
        System.out.println("curr time: " + currTime.toString());
        int currHour = currTime.getHour() + currTime.getMinute() / 60;
        System.out.println("curr hour: " + currHour);
        if (currHour >= DELIVERY_TIME_FRAME_FROM && currHour <= DELIVERY_TIME_FRAME_TO) {
            System.out.println("empty");
            return Optional.empty();
        }

        LocalDateTime idleUntilTime = currTime.toLocalDate().atTime(DELIVERY_TIME_FRAME_FROM, 0);
        if (currHour > DELIVERY_TIME_FRAME_TO) {
            System.out.println("plus 1");
            idleUntilTime = idleUntilTime.plusDays(1);
        }
        while (arrayContains(DELIVERY_TIME_FRAME_OFF_DAYS, idleUntilTime.getDayOfWeek().getValue())) {
            System.out.println("plus 1 + 1");
            idleUntilTime = idleUntilTime.plusDays(1);
        }
        System.out.println(idleUntilTime.toString());
        System.out.println(idleUntilTime.atZone(ZoneOffset.UTC).toEpochSecond());
        long unixTimestamp = idleUntilTime.atZone(ZoneId.of(TIMEZONE_ID)).toEpochSecond();
        System.out.println(Instant.ofEpochSecond(unixTimestamp).atZone(ZoneId.of(TIMEZONE_ID)).toString());
        System.out.println(unixTimestamp);
        return Optional.of(unixTimestamp);
    }

    public static boolean arrayContains(int[] arr, int find) {
        for (int  i = 0; i < arr.length; i++) {
            if (arr[i] == find) {
                return true;
            }
        }
        Integer.parseInt("12.34");
        return false;
    }


}
