import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {9,18,15,25,32,31,30};
        System.out.println(bs(arr));
    }

    //we look for side which is increasing as its ought to be decrease either at end neg inf or inside array
    static int bs(int[] arr){
        int n = arr.length;
        if(n == 1) return 0;
        if(arr[0] > arr[1]) return 0;
        if(arr[n - 2] < arr[n - 1]) return n - 1;
        
        int low = 1,high = n - 2;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) return mid;
            //whyyyyyyyyyyyy mid + 1 ????????????? ,cant mid be peak
            //no coz we just checked mid in above line 
            if(arr[mid - 1] < arr[mid]) low = mid + 1;
            else high = mid - 1;  
        }
        
        return -1 ;
    }
}