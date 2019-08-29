package sort;

import java.util.Arrays;

/**
 * 简单选择排序，时间复杂度为O(n^2)
 * @className：SmipleSelectSort.java
 * @Title:     SmipleSelectSort
 * @Description: TODO 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月19日下午12:31:58
 */
public class SmipleSelectSort {
	public static void main(String[] args) {
		int[] arr = {5,8,7,4,3,9,2,1,6};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 简单排序
	 * @author: dave
	 * @date:   2019年5月19日 下午12:34:06
	 * @Description: 循环数组的每一个元素和后面的每一个元素进行比较
	 * @param arr void  
	 * @throws
	 */
	private static void sort(int[] arr) {
		
		for(int i = 0;i < arr.length;i++){
			for(int j = i;j < arr.length;j++){
				if(arr[i] > arr[j]){
					swap(arr,i,j);
				}
			}
		}
	}

	/**
	 * 交换
	 * @author: dave
	 * @date:   2019年5月19日 下午12:37:52
	 * @Description: 利用数学知识，减少临时变量的使用，减少空间的浪费
	 * @param arr
	 * @param i
	 * @param j void  
	 * @throws
	 */
	private static void swap(int[] arr, int i, int j) {
		
		arr[i] = arr[i] + arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] = arr[i] - arr[j];
	}
}
