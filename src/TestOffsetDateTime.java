import java.time.*;

public class TestOffsetDateTime {

    public void test() {
        String str = "2019-11-01";
        OffsetDateTime o = OffsetDateTime.of(LocalDate.now(), LocalTime.NOON, ZoneOffset.UTC);
        OffsetDateTime o1 = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
    }
}
