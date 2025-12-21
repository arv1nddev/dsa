import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {9,12,15,25,30,31,93};
        int target = 17;

        System.out.println(floor(arr,target));
        System.out.println(ceil(arr,target));
    }
    //floor(x) = largest number <= x,here x is target
    static int floor(int[] arr,int target){
        int n = arr.length;
        int ans = n;

        int low = 0,high = n - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] <= target){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }


    //ceil is nothing but the lower bound i.e ceil(x) = smallest number >= x
    static int ceil(int[] arr,int target){
        int n = arr.length;
        int ans = n;
        
        int low = 0,high = n-1;
        while(low <= high){
            int  mid = ( low + high ) / 2;
            if (arr[mid] >= target){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        
        return ans;
    }
}