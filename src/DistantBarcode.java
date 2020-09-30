import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class DistantBarcode {
    public int[] rearrangeBarcodes(int[] barcodes) {
        // init frequency counter
        PriorityQueue<Barcode> maxHeap = new PriorityQueue<>((b1, b2) -> b2.frequency - b1.frequency);
        Map<Integer, Barcode> frequencyCounter = new HashMap<>();
        for (int code : barcodes) {
            Barcode barcode = frequencyCounter.get(code);
            if (barcode == null) {
                barcode = new Barcode(code, 1);
                frequencyCounter.put(code, barcode);
            } else {
                barcode.frequency ++;
            }
        }
        maxHeap.addAll(frequencyCounter.values());

        // start write result
        int[] res = new int[barcodes.length];
        for (int i = 0; i < res.length; i++) {
            Barcode mostFrequencyBq = maxHeap.poll();
            if (i == 0 || mostFrequencyBq.code != res[i-1]) {
                res[i] = mostFrequencyBq.code;
                mostFrequencyBq.frequency --;
            } else {
                Barcode secondMostFrequencyBq = maxHeap.poll();
                res[i] = secondMostFrequencyBq.code;
                secondMostFrequencyBq.frequency --;
                if (secondMostFrequencyBq.frequency > 0) {
                    maxHeap.add(secondMostFrequencyBq);
                }
            }
            if (mostFrequencyBq.frequency > 0) {
                maxHeap.add(mostFrequencyBq);
            }
        }
        return res;
    }

    static class Barcode {
        int code;
        int frequency;
        public Barcode(int code, int frequency) {
            this.code = code;
            this.frequency = frequency;
        }
    }
}