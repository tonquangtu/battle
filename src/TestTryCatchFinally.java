import java.util.function.Supplier;

public class TestTryCatchFinally {

    public static void main(String[] args) {
        TestTryCatchFinally testTryCatchFinally = new TestTryCatchFinally();
        testTryCatchFinally.runTest();
    }

    public void runTest() {
        System.out.println(Object.class.getName());
        System.out.println(testTryCatchFinally(() -> null));
        boolean exception = testTryCatchFinally(() -> {
            throw new RuntimeException("Exception");
        });
        System.out.println(exception);
    }


    public boolean testTryCatchFinally(Supplier<Object> supplier) {
        System.out.println("---------------");
        try {
            System.out.println("running");
            supplier.get();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            System.out.println("finally");
        }
    }
}
