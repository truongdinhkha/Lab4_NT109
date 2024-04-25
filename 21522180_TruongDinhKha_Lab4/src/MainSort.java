/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class MainSort {
	
	//Tạo mảng với kích thước là 2000 phần tử 
	private static Random random = new Random();
	private static final int size = 2000;
	private static final Integer list[] = new Integer[size];
	// Điền vào mảng ban đầu với các phần tử ngẫu nhiên trong phạm vi
	static {
	for(int i=0; i<size; i++){
		list[i] = random.nextInt(2000);
	} 
	}
	
	public static void main(String[] args){

	System.out.print("Input = [");
	for (Integer each: list)
		System.out.print(each+", ");
	System.out.print("] \n" +"Input.length = " + list.length + '\n');


        // Kiểm tra triển khai sắp xếp hợp nhất đơn luồng tùy chỉnh (hợp nhất đệ quy)
	Integer[] arr2 = Arrays.copyOf(list, list.length);
	long t = System.currentTimeMillis();
	Sort2000.quickSort(arr2, 0, arr2.length-1);
	t = System.currentTimeMillis() - t;
       
	System.out.println("Thoi gian 1 luong : " + t + "ms");
	Log.log("Thoi gian 1 luong : " + t + "ms");
	// Kiểm tra triển khai sắp xếp hợp nhất tùy chỉnh (đa luồng) (hợp nhất đệ quy)
	Integer[] arr = Arrays.copyOf(list, list.length);
	Sort2000.threadedSort(arr);
	System.out.print("Output = [");
	String str="";
	for (Integer each: arr){
		System.out.print(each+", ");
		str+=each+", ";
	}
	Log.log(str);
	System.out.print("]\n");
	}
}
