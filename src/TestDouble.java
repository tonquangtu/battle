public class TestDouble {
    public static void main(String[] args) {
       Double d = 24.49;
       Double f = d;
        System.out.println(d);
        System.out.println(f);

        //String s = "0001010";
        String s = "123e";
        System.out.println(s.replaceAll("^0*", ""));
        System.out.println(s.replaceAll("[*e]", "{\n"
            + "\"target\": \"TPS\",\n"
            + "\"job_key\": \"105201321497219746\",\n"
            + "\"endpoint\": \"OLD_TPS_CREATE_PICKUP_REQUEST\",\n"
            + "\"status\": \"NETWORK_FAILED\",\n"
            + "\"from\": 1588554000000\n"
            + "}"));
    }
}
