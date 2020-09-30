class PerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        int sqrtNum = psWithBinarySearch(num, 2, num/2);
        return sqrtNum != -1;
    }

    int psWithBinarySearch(int num, int from, int to) {
        if (from > to) {
            return -1;
        }
        int medium = (from + to) / 2;
        float dividedVal = num / (float)medium;
        if (dividedVal == medium) {
            return medium;
        } else if (dividedVal < medium) {
            return psWithBinarySearch(num, from, medium - 1);
        } else {
            return psWithBinarySearch(num, medium + 1, to);
        }
    }

    public static void main(String[] args) {
        PerfectSquare ps = new PerfectSquare();
        boolean res = ps.isPerfectSquare(5);
        System.out.println(res);
    }
}