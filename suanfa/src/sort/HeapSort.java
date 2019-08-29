package sort;

import java.util.Arrays;

/**
 * 堆排序，不稳定
 * @className：HeapSort.java
 * @Title:     HeapSort
 * @Description: 时间复杂度为O(nlgn) ，升序采用大顶堆，降序采用小顶堆
 * @Company:   dave集团
 * @author:    dave
 * @version:   v1.0
 * @date:      2019年5月19日上午10:27:18
 */
public class HeapSort {

	public static void main(String[] args) {
//		int[] arr = {9,8,7,6,5,4,3,2,1};
		int[] arr = {2,4,6,1,3,8,9,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 
	 * @author: dave
	 * @date:   2019年5月19日 上午10:30:35
	 * @Description: TODO
	 * @param arr void  
	 * @throws
	 */
	private static void sort(int[] arr) {
		//1.构建大顶堆
		for(int i=arr.length/2-1;i>=0;i--){
			//从第一个非叶子节点即父节点开始从下至上从左到右依次调整结构
			//adjustHeap(arr,i,arr.length);//调整堆结构升序
			adjustDescHeap(arr,i,arr.length);//调整堆结构降序
			
		}
		//2.调整对结构+交换顶堆和末尾元素
		for(int j=arr.length-1;j>0;j--){
			swap(arr,0,j);//将顶点元素与末尾元素进行交换
			
			//adjustHeap(arr,0,j);//调整堆结构升序
			adjustDescHeap(arr,0,j);//调整堆结构降序
		}
		
	}
	

	/**
	 * 调整大顶堆，建立在大顶堆以构建的基础上
	 * @author: dave
	 * @date:   2019年5月19日 上午10:41:45
	 * @Description: TODO
	 * @param arr
	 * @param i
	 * @param length void  
	 * @throws
	 */
	private static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];//取出当前i元素
		for(int k=2*i+1;k<length;k=2*k+1){//从i结点的左儿子结点开始调整
			if(k<length-1&&arr[k]<arr[k+1]){//如果左子节点小于右子节点，k指向k+1
				k=k+1;
			}
			if(arr[k]>arr[i]){
				arr[i] = arr[k];
				arr[k] = temp;
			}else{
				break;
			}
			i=k;
		}
	}
	
	/**
	 * 交换堆顶和堆末元素
	 * @author: dave
	 * @date:   2019年5月19日 上午11:00:14
	 * @Description: TODO
	 * @param arr
	 * @param i
	 * @param j void  
	 * @throws
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}
	
	/******************以下调整小顶堆，用于降序排序
	/**
	 * 调整小顶堆，用于降序
	 * @author: dave
	 * @date:   2019年5月19日 下午12:22:32
	 * @Description: TODO
	 * @param arr
	 * @param i
	 * @param length void  
	 * @throws
	 */
	private static void adjustDescHeap(int[] arr, int i, int length) {
		int temp = arr[i];//取出当前i元素
		for(int k=2*i+1;k<length;k=2*k+1){//从i结点的左儿子结点开始调整
			if(k<length-1&&arr[k]>arr[k+1]){//如果左子节点小于右子节点，k指向k+1
				k=k+1;
			}
			if(arr[k]<arr[i]){
				arr[i] = arr[k];
				arr[k] = temp;
			}else{
				break;
			}
			i=k;
		}
	}
}
