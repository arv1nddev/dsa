import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {9,12,15,25,30,31,93};
        int target = 15;

        System.out.println(bs(arr,target));
    }

    //in each iteration we halve the search space if element at mid is smaller than target than target is gotta be in right side
    //if element at mid is greater than target than target is gotta be in left side,since array is sorted
    static int bs(int[] arr,int target){
        int n = arr.length;
        
        int low = 0,high = n-1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1 ;
    }
}