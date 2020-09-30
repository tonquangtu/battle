import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestListContains {

    public static void main(String[] args) {
        TestListContains  tester = new TestListContains();
        tester.test();
    }


    public void test() {
        List<UUID> uuids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            uuids.add(UUID.randomUUID());
        }

        UUID testUUID = UUID.fromString(new String(uuids.get(0).toString()));
        System.out.println(uuids.contains(testUUID));
    }

}
