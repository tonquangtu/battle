import java.util.ArrayList;

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (s.length() == 0) {
            return "";
        }

        if (s.length() == 1 || numRows == 1) {
            return s;
        }
        StringBuilder [] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < numRows - 1 && i < s.length(); j++) {
                builders[j].append(s.charAt(i));
                i ++;
            }

            for (int j = numRows - 1; j > 0 && i < s.length(); j --) {
                builders[j].append(s.charAt(i));
                i++;
            }
            i --;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            res.append(builders[i].toString());
        }
        return res.toString();
    }
}

class TestZigZagConversion {
    public static void main(String[] args) {
        var zigzag = new ZigZagConversion();
        var list = new ArrayList<String>();
        var nums = new ArrayList<Integer>();
        var resList = new ArrayList<String>();

        // PAHNAPLSIIGYIR
//        list.add("PAYPALISHIRING");
//        nums.add(3);
//        resList.add("PAHNAPLSIIGYIR");
//
//        list.add("PAYPALISHIRING");
//        nums.add(4);
//        resList.add("PINALSIGYAHRPI");

        list.add("A");
        nums.add(1);
        resList.add("A");

        list.add("AA");
        nums.add(1);
        resList.add("AA");

        for (int i = 0; i < list.size(); i++) {
            var output = zigzag.convert(list.get(i), nums.get(i));
            if (!output.equals(resList.get(i))) {
                System.out.println("Input: " + list.get(i));
                System.out.println("Output: " + output);
                System.out.println("Expected: " + resList.get(i));
                System.out.println("-------");
            }
        }
    }
}
