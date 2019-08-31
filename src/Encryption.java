import java.util.ArrayList;
import java.util.Arrays;

public class Encryption {

    String encryption(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        String notSpaceS = s.replaceAll(" ", "");
        int length = notSpaceS.length();
        double sqrt = Math.sqrt(length);
        int low = (int)Math.floor(sqrt);
        int high = (int)Math.ceil(sqrt);

        int row, column;
        if (low == high) {
            row = column = low;
        } else {
            row = low;
            column = high;
        }

        ArrayList<ArrayList<Character>> grid = new ArrayList<>();
        for (int i = 0; i < length; ) {
            ArrayList<Character> rowChar = new ArrayList<>();
            for (int j = 0; j < column && i < length; j ++) {
                rowChar.add(notSpaceS.charAt(i));
                i ++;
            }
            grid.add(rowChar);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < column; i++) {
            StringBuilder partBuilder = new StringBuilder();
            for (int j = 0; j < grid.size(); j++) {
                if (grid.get(j).size() > i) {
                    partBuilder.append(grid.get(j).get(i));
                }
            }
            partBuilder.append(" ");
            builder.append(partBuilder.toString());
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        var encryption = new Encryption();
        var in = "if man was meant to stay on the ground god would have given us roots";
        var out = "imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau";
        var result = encryption.encryption(in);
        System.out.println(result);
        System.out.println(out);

    }



}
