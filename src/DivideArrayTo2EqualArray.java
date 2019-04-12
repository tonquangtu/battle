public class DivideArrayTo2EqualArray {

    public void divide2Arrays(int [] arr, int [] leftArr, int [] rightArr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum % 2 != 0) {
            System.out.println("Can not divide to 2 equals array");
            return;
        }

        int halfSum = sum / 2;
        for (int i = 0; i < arr.length; i++) {
            
        }





    }
}
