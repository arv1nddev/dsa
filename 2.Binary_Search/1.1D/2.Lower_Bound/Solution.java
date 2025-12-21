import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {9,12,15,25,30,31,93};
        int x = 32;
        Integer X = 32;

        System.out.println(bs(arr,x));
        System.out.println(bs(arr,X));
    }

    //we need to find smallest index or smallest nubmer which is greater than or equal to x in nums else the size of array
    static int bs(int[] arr,int x){
        int n = arr.length;
        
        int low = 0,high = n-1;
        while(low < high){
            int mid = ( low + high ) / 2;

            //if mid is smaller than x then its definetly not our answer as we need atleast equal or greater , so we exclude this index
            if( arr[mid] < x){
                low = mid + 1;
            }
            //now mid of arr is either equal or greater than x,hence it is a potential index so we include it,while searching
            else{
                high = mid;
            }
        }
        
        return arr[high] >= x ? high : n ;
    }
    
    //more readable,simple and standard
    static int bs(int[] arr,Integer x){
        int n = arr.length;
        int ans = n;
        
        int low = 0,high = n-1;
        //we also check even if low == high,while the upper one goes to infinite loop for arr[mid] >= target && low == high
        while(low <= high){
            int mid = ( low + high ) / 2;

            //if mid is smaller than x then its definetly not our answer as we need atleast equal or greater ,so we exclude this index(mid)
            if( arr[mid] < x){
                low = mid + 1;
            }
            //now mid of arr is either equal or greater than x,hence it is a potential index so we update our ans and exclude it too (mid)
            else{
                ans = mid;
                high = mid - 1;
            }
        }
        
        return ans;
    }
}