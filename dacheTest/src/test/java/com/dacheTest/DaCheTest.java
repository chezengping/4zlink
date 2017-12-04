package com.dacheTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.dache.Application;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Slf4j
public class DaCheTest {

	/**
	 * 冒泡排序
	 */
	@Test
	public void testA(){
		int[] arr={5,2,6,3,1,8,7};
		for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
		
	}
	
	
	/**
	 * 选择排序
	 */
	@Test
	public void testB(){
		int[] arr={5,2,6,3,1,8,7};
		 for (int i = 0; i < arr.length; i++) {
	            int least = i;
	            for (int j = i + 1; j < arr.length; j++) {
	                if (arr[j] < arr[least]) {
	                    least = j;
	                }
	            }
	            // 将当前第一个元素与它后面序列中的最小的一个 元素交换，也就是将最小的元素放在最前端
	            int temp = arr[i];
	            arr[i] = arr[least];
	            arr[least] = temp;
	        }
		
	}
	
	
	
	/**
	 * 插入排序
	 */
	@Test
	public void testC(){
		
		int[] arr={5,2,6,3,1,8,7};
		
		int in,out;
        for(out = 1 ; out < arr.length ; out ++){
            int temp = arr[out];
            in = out;
            while(in > 0 && arr[in-1] >= temp){
                arr[in] = arr[in - 1];
                --in;
            }
            arr[in] = temp;
        }
        
        for (int i : arr) {
        	System.out.println(i);
		}
	}
	
	/**
	 * 归并排序
	 */
	int[] theArray={5,2,6,3,1,8,7};
	
	@Test
	public void testD(){
		
		
		int [] workSpace = new int [theArray.length];
        reMergeSort(workSpace, 0, theArray.length-1);
        
        
        System.out.println(Arrays.toString(theArray));
	}
	
	
	private void reMergeSort(int [] workSpace,int lowerBound,int upperBound) {
        if(lowerBound == upperBound){
            return;
        }else{
            int mid = (lowerBound + upperBound) / 2;
            reMergeSort(workSpace, lowerBound, mid);
            reMergeSort(workSpace, mid + 1, upperBound);
            merge(workSpace, lowerBound, mid + 1,upperBound);
        }
    }

    private void merge(int [] workSpace,int lowPtr,int highPtr,int upperBound){
        int j= 0;//workSpace's index
        int lowerBound = lowPtr;
        int mid = highPtr -1;
        int n = upperBound - lowerBound + 1;
        while(lowPtr <= mid && highPtr <= upperBound){
            if(theArray[lowPtr] < theArray[highPtr]){
                workSpace[j++] = theArray[lowPtr++];
            }else{
                workSpace[j++] = theArray[highPtr++];
            }
        }
        while(lowPtr <= mid){
            workSpace[j++] = theArray[lowPtr++];
        }
        while(highPtr <= upperBound){
            workSpace[j++] = theArray[highPtr++];
        }
        for(j = 0;j < n ;j++){
            theArray[lowerBound+j] = workSpace[j];
        }
    }
    ///////////////////////////////以上属于归并排序///////////////////////////////////////
    
    
    /**
     * 希尔排序
     */
    @Test
    public void testE(){
    	int[] arr={5,2,6,3,1,8,7};
    	int inner, outer;
        int temp;
        int h = 1;
        int nElem = arr.length;
        while (h <= nElem / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (outer = h; outer < nElem; outer++) {
                temp = arr[outer];
                inner = outer;
                while (inner > h - 1 && arr[inner - h] >= temp) {
                    arr[inner] = arr[inner - h];
                    inner -= h;
                }
                arr[inner] = temp;
            }
            h = (h - 1) / 3;
        }
        for (int i : arr) {
        	System.out.println(i);
		}
    }
    
    
    /**
     * 快速排序
     */
    @Test
    public void testF(){
    	int[] arr={5,2,6,3,1,8,7};
    	
    	int left=0;
    	int right=arr.length-1;
    	
        recQuickSort(arr, left,right);
        
        for (int i : arr) {
        	System.out.println(i);
		}
    }
    
    //快速排序
    private void recQuickSort(int arr [] ,int left,int right){
        if(right - left <= 0){
            return;
        }else{
            int pivot = arr[right];//一般使用数组最右边的元素作为枢纽
            int partition = partitionIt(arr, left, right, pivot);
            recQuickSort(arr, left, partition-1);
            recQuickSort(arr, partition+1, right);
        }
    }
    
    //划分
    private static int partitionIt(int[] arr ,int left,int right,int pivot){
        int leftPtr = left - 1;
        //int rightPtr = right + 1;
        int rightPtr = right ; //使用最右边的元素作为枢纽，划分时就要将最右端的数据项排除在外
        while(true){
            while(arr[++leftPtr] < pivot);
            while(rightPtr > 0 && arr[--rightPtr] > pivot);

            if(leftPtr >= rightPtr){
                break;
            }else{
                //交换leftPtr和rightPtr位置的元素
                int temp = arr[leftPtr];
                arr[leftPtr] = arr[rightPtr];
                arr[rightPtr] = temp;
            }
        }
        //交换leftPtr和right位置的元素
        int temp = arr[leftPtr];
        arr[leftPtr] = arr[right];
        arr[right] = temp;
        return leftPtr;//返回枢纽位置
    }
    
    ///////////////////////////以上为快速排序/////////////////////////////////
    
    
    
    
}
