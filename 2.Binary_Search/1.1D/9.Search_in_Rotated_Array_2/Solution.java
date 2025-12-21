import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {25,30,31,93,2,3,7,9,12,15};
        int target = 17;

        if(bs(arr,target)) System.out.println("exists");
        else System.out.println("doesnt exists");
    }

    //we break down the array into two parts one sorted and other unsorted (always holds except when element at low == mid == high)
    ////uique condition which occurs only when duplicates exists in array,and we handle it simply by skipping low and high(target cant be there)
    //else there will two parts sorted & unsorted ,so we use the property of sorted array since unsorted array cant be used to narrow down our search
    //its just bs in rotated sorted array with one extra condition
    static boolean bs(int[] arr, int target) {
        int low = 0,high = arr.length - 1;
        while(low <= high){
            int mid = (low + high) / 2;

            if(arr[mid] == target) return true;
            //uique condition which occurs only when duplicates exists in array
            else if(arr[low] == arr[mid] && arr[mid] == arr[high]){
                low++;
                high--;
            //else find the sorted half and narrow down search space
            }else if(arr[low] <= arr[mid]){
                if(arr[low] <= target && target < arr[mid]) high = mid - 1;
                else low = mid + 1;
            }else{
                if(arr[mid] < target && target <= arr[high]) low = mid + 1;
                else high = mid - 1;
            }
        }
        return false;
    }
}