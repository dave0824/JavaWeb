package sort;

import java.util.Arrays;

/**
 * 插入排序，时间复杂度O(n^2),稳定
 * @className：InsertionSort.java
 * @Title:     InsertionSort
 * @Description: TODO 
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月19日下午1:41:12
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = {2,4,6,1,3,8,9,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 插入排序
	 * @author: dave
	 * @date:   2019年5月19日 下午1:42:04
	 * @Description: TODO
	 * @param arr void  
	 * @throws
	 */
	private static void sort(int[] arr) {
		int v,j;
		for(int i=1;i<arr.length;i++){
			v=arr[i];//v记录要插入的元素
			j=i;
			while(j>=1 && arr[j-1]>v){
				//为要插入的元素腾出空间
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = v;
		}
		
	}
}
