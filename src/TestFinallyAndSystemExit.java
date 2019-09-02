public class TestFinallyAndSystemExit {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1() {
        try {
            System.out.println("Step 1");
            System.exit(0);
            System.out.println("Step 2");
        } catch (Exception e) {
            System.out.println("Catch");
        } finally {
            System.out.println("Finally");
        }
    }

    public static void test2() {
        try {
            System.out.println("Step 1");
            Integer.parseInt("not int");
        } catch (Exception e) {
            System.out.println("Catch");
            System.exit(0);
        } finally {
            System.out.println("Finally");
        }
    }
}
