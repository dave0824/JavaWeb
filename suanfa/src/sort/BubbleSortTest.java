package sort;

import java.util.Arrays;

public class BubbleSortTest {
	public static void main(String[] args) {
		int[] arr = {10,20,30,40,50,60};
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
			System.out.println("第"+ i +"趟冒泡");
			int k = 1;
			for(int j = 0;j<arr.length - i;j++){
				if(arr[j]>arr[j+1]){
					swap(arr,j,j+1);
					System.out.printf("第%d次交换",k++);
					System.out.println(Arrays.toString(arr));
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
