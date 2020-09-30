class MySqrt {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        return calculateSqrt(x, 1, x/2);
    }

    private int calculateSqrt(int x, int from, int to) {
        if (from + 1 >= to) {
            if (x / (double)to >= to) {
                return to;
            }
            return from;
        }
        int medium = (from + to)/2;
        double divided = x / (double)medium;
        if (divided == medium) {
            return medium;
        } else if (divided > medium) {
            return calculateSqrt(x, medium, to);
        } else {
            return calculateSqrt(x, from, medium);
        }
    }
}