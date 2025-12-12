import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[] arr = new int[] {0,1,2,0,1,2,1,2,0,0,0,1};

        // sort(arr);
        // sort(arr,0);
        sort(arr,true);

        System.out.println(Arrays.toString(arr));
    }

    //brute is just sort ,tc = O(nlogn) for merge sort ,sc = O(n)

    //better,thanks college
    static void sort(int [] arr){
        int [] counting = new int[3];

        for(int num : arr) counting[num]++;
        
        for(int i =0,index =0;i<3;i++){
            while(counting[i]>0){
                arr[index++] = i;
                counting[i]--;
            }
        }
    }
    //tc = O(n),sc = O(1)
    
    //more asthetic
    static void sort(int[] arr,int forPolymorphism){
        int n = arr.length;
        
        int [] counting = new int[3];
        for(int num : arr) counting[num]++;
        counting[1] += counting[0];
        counting[2] += counting[1];
        
        int[] sorted  = new int[n] ;
        
        for(int i = 0;i<n; i++)  sorted[ --counting[arr[i]] ] = arr[i];
        
        for(int i =0;i<n;i++) arr[i] = sorted[i];
    }
    //tc = O(n),sc = O(n)
    
    //optimal : Dutch National Flag Algorithm
    // it used 3 pointers low,mid and high such that 
    //                                                 0 -> low-1 has only 0's
    //                                                 low -> mid-1 has only 1's
    //                                                 mid -> high is unosrted
    //                                                 high+1 -> n-1 has only 2's
    
    //        0....low-1  low....mid-1  mid....high   high+1....n-1
    //        0,0,0,0,0,  1,1,1,1,1,1,   unsorted      ,2,2,2,2,2,2
    
    static void sort(int[] arr,boolean forPolymorphism){
        int n = arr.length;

        int low =0,mid =0,high = n-1;

        while(mid<=high){
            
            //self understandable,from above diagram
            if(arr[mid] == 0){
                swap(arr,low,mid);
                low++;
                mid++;
            }else if(arr[mid] == 1){
                mid++;
            }else{
                swap(arr,mid,high);
                high--;
            }
        }
    }
    //in each iteration ,either mid increases or high decreases and both start from 0 and n-1,so it would run exactly n times
    //tc = O(n),sc = O(1)

    static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
