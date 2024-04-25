import java.text.DecimalFormat;
import java.util.ArrayList;
public class Sort2000 extends Thread {
    private static DecimalFormat df = new DecimalFormat("0.#####");
    private static final int MAX_THREADS = 4;

    Sort2000(Integer[] array, int begin, int end){
        super(() -> {
            quickSort(array, begin, end);
        });
        this.start();
    }

    public static void quickSort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    private static void swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void threadedSort(Integer[] array) {
        long startTime = System.currentTimeMillis();

        final int length = array.length;
        boolean exact = length % MAX_THREADS == 0;
        int maxChunkSize = exact ? length / MAX_THREADS : length / (MAX_THREADS - 1);
        maxChunkSize = Math.max(maxChunkSize, MAX_THREADS);

        final ArrayList<Sort2000> threads = new ArrayList<>();

        for (int i = 0; i < length; i += maxChunkSize) {
            int begin = i;
            int remain = length - i;
            int end = remain < maxChunkSize ? i + (remain - 1) : i + (maxChunkSize - 1);
            final Sort2000 t = new Sort2000(array, begin, end);
            threads.add(t);
        }

        for (Sort2000 t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < length; i += maxChunkSize) {
            int mid = i == 0 ? 0 : i - 1;
            int remain = length - i;
            int end = remain < maxChunkSize ? i + (remain - 1) : i + (maxChunkSize - 1);
            merge(array, 0, mid, end);
        }

        long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime);
        System.out.println("Thoi gian chay " + MAX_THREADS + " luong: " + df.format(elapsedTime) + "ms");
        Log.log("Thoi gian chay " + MAX_THREADS + " luong: " + df.format(elapsedTime) + "ms");
    }
    
    public static void merge(Integer[] array, int begin, int mid, int end){
        Integer[] temp = new Integer[(end - begin) + 1];
        
        int i = begin, j = mid + 1;
        int k = 0;

        while(i <= mid && j <= end){
            if (array[i] <= array[j]){
                temp[k] = array[i];
                i += 1;
            } else{
                temp[k] = array[j];
                j += 1;
            }
            k += 1;
        }

        while(i <= mid){
            temp[k] = array[i];
            i += 1; 
            k += 1;
        }
        
        while(j <= end){
            temp[k] = array[j];
            j += 1; 
            k += 1;
        }

        for(i = begin, k = 0; i <= end; i++, k++){
            array[i] = temp[k];
        }
    }
}