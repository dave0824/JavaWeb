package sort;

import java.util.Arrays;

/**
 * 冒泡排序，时间复杂度O(n^2),稳定
 * @className：BubbleSort.java
 * @Title:     BubbleSort
 * @Description: TODO 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年6月17日13:03:06
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = {20,40,30,10,60,50};
		System.out.println("起始："+Arrays.toString(arr));
		sort(arr);
		System.out.println("结束："+Arrays.toString(arr));
	}

	/**
	 * 冒泡排序
	 * @author: dave
	 * @date:   2019年5月19日 下午12:48:26
	 * @Description: TODO
	 * @param arr void  
	 * @throws
	 */
	private static void sort(int[] arr) {
		
		
		for(int i = 1;i < arr.length ;i++){
			for(int j = 0;j<arr.length - i;j++){
				if(arr[j]>arr[j+1]){
					swap(arr,j,j+1);
				}
			}
		}
	}

	private static void swap(int[] arr, int j, int i) {
		arr[i] = arr[i] + arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] = arr[i] - arr[j];
		
	}
}
