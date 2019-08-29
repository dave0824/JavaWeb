package sort;

import java.util.Arrays;

/**
 * 快速排序，时间复杂度O(nlgn),不稳定
 * @className：QuickSort.java
 * @Title:     QuickSort
 * @Description: TODO 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月19日下午12:58:29
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] arr = {2,4,6,1,3,8,9,5,7};
		quick(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 快速选择排序
	 * @author: dave
	 * @date:   2019年5月19日 下午1:00:49
	 * @Description: TODO
	 * @param arr void  
	 * @throws
	 */
	private static void quick(int[] arr,int low ,int high) {
		
		if(low < high){
			int mid = getMiddle(arr,low,high);
			quick(arr,low,mid-1);
			quick(arr,mid+1,high);
		}
		
	}

	/**
	 * 在数组元素中去一个值为分界值，比这个大的值放它右边，比它小的放左边
	 * @author: dave
	 * @date:   2019年5月19日 下午1:15:15
	 * @Description: TODO
	 * @param arr
	 * @param low
	 * @param high
	 * @return int  
	 * @throws
	 */
	private static int getMiddle(int[] arr, int low, int high) {
		int temp = arr[low];
		while(low < high){
			while(low < high && arr[high] >= temp){//如果右边比边界值大，就high--一直往前找
				high--;
			}
			if(arr[high] < temp ){//如果比边界值小，就将值赋给arr[low]
				arr[low] = arr[high];
				low++;//因为右边空出一个位置，所以让low++寻找一个比边界值大的放过来
			}
			
			while(low < high && arr[low] <= temp){//如果左边比边界值小，就low++一直往后找
				low++;
			}
			
			if(arr[low] > temp){//如果比边界值大，就将值赋给arr[high]
				arr[high] = arr[low];
				high--;//因为右边空出一个位置，所以让high--寻找一个比边界值小的放过来
			}
		}
		arr[low] = temp;
		return low;
	}
}
