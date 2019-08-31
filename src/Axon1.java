public class Axon1 {
    public static void main(String[] args) {
        var axon = new Axon1();
//        System.out.println(axon.solution(53, 1953786));
//        System.out.println(axon.solution(78, 195378678));
//        System.out.println(axon.solution(57, 153786));
//        System.out.println(axon.solution(0, 1537860));
//        System.out.println(axon.solution(0, 0120));
//        System.out.println(axon.solution(0, 0120));
        System.out.println(axon.solution("Codility We test coders", 14));
        System.out.println(axon.solution("Why not", 100));
        System.out.println(axon.solution("The quick brown fox jumps over the lazy dog", 39));
        System.out.println(axon.solution("the tu", 3));
    }

    public int solution(int A, int B) {
        // write your code in Java SE 8
        String strA = String.valueOf(A);
        String strB = String.valueOf(B);
        System.out.println(strA);
        System.out.println(strB);
        return strB.indexOf(strA);
    }

    public String solution(String message, int K) {
        // write your code in Java SE 8
        if (K >= message.length()) {
            return message;
        }
        if (message.charAt(K) == ' ') {
            return message.substring(0, K);
        }
        int index = K - 1;
        while (index > 0 && message.charAt(index) != ' ') {
            index -= 1;
        }
        return index > 0 ? message.substring(0, index) : "";
    }
}
