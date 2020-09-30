package test;

class TestString {
    public int myAtoi(String str) {
        CleanerNum cleanerNum = CleanerNum.cleanNum(str);
        if (cleanerNum.num.length() == 0) {
            return 0;
        }
        return parseInt(cleanerNum.num, cleanerNum.sign);
    }

    public int parseInt(String s, int sign) {
        if (s.length() > 10) {
            return wrapExceededNum(sign);
        }
        long num = Long.parseLong(s);
        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
            return wrapExceededNum(sign);
        }
        return sign * (int)num;
    }

    int toInt(char c) {
        return c - '0';
    }

    int wrapExceededNum(int sign) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    }

    static class CleanerNum {
        String num;
        int sign;
        boolean hasExponential;

        public CleanerNum(String num, int sign, boolean hasExponential) {
            this.num = num;
            this.sign = sign;
            this.hasExponential = hasExponential;
        }

        public static CleanerNum cleanNum(String s) {
            StringBuilder builder = new StringBuilder();
            int sign = 1;
            boolean isMetDigit = false;
            boolean isMetSign = false;
            boolean isMetExponential = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    builder.append(c);
                    isMetDigit = true;
                } else if (isExponential(c) && isMetDigit && i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    builder.append(c);
                    isMetExponential = true;
                } else if (isSign(c) && !isMetSign && !isMetDigit) {
                    sign = c == '-' ? -1 : 1;
                    isMetSign = true;
                } else if (isIgnore(c) && !isMetSign && !isMetDigit) {
                    continue;
                } else {
                    break;
                }
            }
            return new CleanerNum(builder.toString().replaceFirst("0*", ""), sign, isMetExponential);
        }

        public static boolean isSign(char c) {
            return c == '-' || c == '+';
        }

        public static boolean isIgnore(char c) {
            return c == ' ';
        }

        public static boolean isExponential(char c) {
            return c == 'e';
        }
    }
}