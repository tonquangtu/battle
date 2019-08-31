public class ChocolateBarCuttingUseBinarySearch {

    public static void main(String[] args) {
        var cutter = new ChocolateBarCuttingUseBinarySearch();
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 3, 2, 4, 4}, 3) + " -> 5"); // expect 5
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 3, 4, 4}, 3) + " -> 4"); // expect 4
        System.out.println(cutter.cuttingChocolate(new int[] {3, 4, 5, 3, 3, 2}, 3) + " -> 5"); // expect 5
        System.out.println(cutter.cuttingChocolate(new int[] {2, 2, 2, 2, 2, 2}, 6) + " -> 2"); // expect 2
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 4, 5, 6}, 4) + " -> 4"); // expect 4
        System.out.println(cutter.cuttingChocolate(new int[] {1, 2, 3, 4, 5, 6}, 3) + " -> 6"); // expect 6
        System.out.println(cutter.cuttingChocolate(new int[] {1, 4, 0, 4, 4, 9}, 2) + " -> 9"); // expect 9
        System.out.println(cutter.cuttingChocolate(new int[] {1, 4, 0, 4, 4, 9}, 4) + " -> 4"); // expect 4
        System.out.println(cutter.cuttingChocolate(new int[] {1, 4, 0, 4, 4, 9}, 6) + " -> 0"); // expect 0
        System.out.println(cutter.cuttingChocolate(new int[] {1, 3, 3, 3, 0, 30}, 3) + " -> 4"); // expect 4
    }

    private int cuttingChocolate(int[] bar, int k) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int[] searchBar = new int[bar.length + 1];
        searchBar[0] = 0;
        for (int i = 0; i < bar.length; i++) {
            sum += bar[i];
            searchBar[i + 1] = sum;
            if (min > bar[i]) {
                min = bar[i];
            }
        }

        int maxSweet = 0;
        int avg = sum / k;
        for (int sweet = min; sweet <= avg; sweet ++) {
            if (tryCut(sweet, searchBar, k)) {
                maxSweet = sweet;
            }
        }
        return maxSweet;
    }

    /**
     * Use greedy to count maximum number of pieces can cut that each piece have sweetness level >= sweet
     */
    private boolean tryCut(int sweet, int[] searchBar, int k) {
        for (int i = 0; i < searchBar.length; i++) {
            if (searchBar[i] >= sweet) {
                int maxPieceCanCut = countLeft(searchBar, i, sweet) + countRight(searchBar, i, sweet);
                if (maxPieceCanCut >= k) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     Use binary search to count maximum number of piece can cut in the left side
     */
    private int countLeft(int[] searchBar, int pos, int sweet) {
        int count = 0;
        while (pos >= 0) {
            int foundPos = findLeft(searchBar, 0, pos, searchBar[pos] - sweet);
            if (foundPos != -1) {
                count += 1;
                pos = foundPos < pos ? foundPos : foundPos - 1;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * find the maximum element that still lesser than findVal (max(element) <= findVal)
     * return position of that element if found, otherwise return -1
     */
    private int findLeft(int[] arr, int start, int end, int findVal) {
        if (start > end) {
            return -1;
        }
        if (end - start <= 1) {
            return arr[end] <= findVal ? end : (arr[start] <= findVal ? start : -1);
        }
        int medium = (start + end) / 2;
        if (arr[medium] == findVal) {
            return medium;
        } else if (arr[medium] > findVal) {
            return findLeft(arr, start, medium - 1, findVal);
        } else {
            return findLeft(arr, medium, end, findVal);
        }
    }

    /**
     Use binary search to count maximum number of piece can cut in the right side
     */
    private int countRight(int[] searchBar, int pos, int sweet) {
        int count = 0;
        while (pos < searchBar.length) {
            int foundPos = findRight(searchBar, pos, searchBar.length - 1, searchBar[pos] + sweet);
            if (foundPos != -1) {
                count += 1;
                pos = foundPos > pos ? foundPos : foundPos + 1;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * find the minimum element that still greater than findVal (min(element) >= findVal)
     * return position of that element if found, otherwise return -1
     */
    private int findRight(int[] arr, int start, int end, int findVal) {
        if (start > end) {
            return -1;
        }
        if (end - start >= 1) {
            return arr[start] >= findVal ? start : (arr[end] >= findVal ? end : -1);
        }
        int medium = (start + end) / 2;
        if (arr[medium] == findVal) {
            return medium;
        } else if (arr[medium] > findVal) {
            return findRight(arr, start, medium, findVal);
        } else {
            return findRight(arr, medium + 1, end, findVal);
        }
    }
}
