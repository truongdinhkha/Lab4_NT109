

import java.util.Scanner;

public class App 
{
	

	public static void main(String[] args)
	{
		Init init = new Init();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Seclect 1. Single Thread; 2. Multi Thread");
                Log.log("Chon gia tri 1 hoac 2 de thuc thi");
		int choose = scanner.nextInt();
		switch (choose)
		{
			
			case 1: 
			{
                            
                                Log.log("Mang duoc tao: ");
				System.out.println("Input Array:"); 
                                
				Thread mainThread = new Thread(new MyThread(0, init.arr.length, init.arr, "Main Thread"));
				mainThread.start();
                                break;   
                                
			}

			case 2: 
			{
                                Log.log("Co 4 thread: ");
				Thread th1 = new Thread(new MyThread(0, 499, init.arr, "Thread 1"));
				Thread th2 = new Thread(new MyThread(500, 999, init.arr, "Thread 2"));
				Thread th3 = new Thread(new MyThread(1000, 1499, init.arr, "Thread 3"));
				Thread th4 = new Thread(new MyThread(1500, 1999, init.arr, "Thread 4"));
				
				th1.start();
                System.out.println("\n\n\n");
				th2.start();
                System.out.println("\n\n\n");
				th3.start();
                System.out.println("\n\n\n");
				th4.start();                            
				break;				
			}
			default: 
				throw new IllegalArgumentException("Unexpected value: " + choose);
                                
		}
	}
}
