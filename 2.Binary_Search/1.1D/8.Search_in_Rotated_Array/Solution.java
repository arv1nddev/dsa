import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {25,30,31,93,2,3,7,9,12,15};
        int target = 15;

        System.out.println(bs(arr,target));
    }

    //we break down the array into two parts one sorted and other unsorted (always holds)
    //then we use the property of sorted array since unsorted array cant be used to narrow down our search
    //its just bs with little modification
    static int bs(int[] arr, int target) {
        int low=0,high=arr.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]==target)return mid;

            if(arr[low]<=arr[mid]){
                if(target>=arr[low] && target<arr[mid]) high=mid-1;
                else low = mid+1;
            }else {
                if(target>arr[mid] && target<=arr[high]) low=mid+1;
                else high = mid-1;
            }
        }
        return -1;
    }
}