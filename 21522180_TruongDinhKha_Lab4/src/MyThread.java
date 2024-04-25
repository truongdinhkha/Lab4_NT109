public class MyThread implements Runnable 
{

	int start;
	int end;
	int []arr;
	String name;
	String state = " input";
	
	public MyThread(int start, int end, int[] arr, String name)
	{
		this.start = start;
		this.end = end;
		this.arr = arr;
		this.name = name;
	}
	
	//hàm sort
	public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

  
	
	//Hàm print
	protected void print()
	{
		String str = this.name + state + ":		";
		for(int i = start; i<end; i++)
		{
			str += arr[i]+ " ";
		}
		System.out.println(str);
	}
	
	public void run()
	{
		print();
		quickSort(arr);
		state = " output";
		print();
                
        }
}
