import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {9,12,15,25,30,31,93};
        int target = 13;

        System.out.println(findposition(arr,target));
        System.out.println(insert(arr,target));
    }

    //we find lower bound
    static int findposition(int[] arr,int target){
        int n = arr.length;
        int ans = n;
        
        int low = 0,high = n - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            
            if(arr[mid] >= target){
                ans = mid;
                high = mid - 1;
            }else low = mid + 1;
        }
        return ans;
    }

    //if upon finding target index is asked to return,above one also works though
    //valid too and less iteration as we return as soon as we find the index
    static int insert(int[] arr,int target){
        int n = arr.length;
        int ans = n;

        int low = 0,high = n - 1;
        while(low <= high){
            int mid = (low + high) / 2;

            if(arr[mid] == target) return mid;
            else if(arr[mid] >= target){
                ans = mid;
                high = mid - 1;
            }else low = mid + 1;
        }
        return ans;
    }
}