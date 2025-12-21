import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {9,12,15,25,30,31,93};
        int x = 32;
        Integer X = 32;

        System.out.println(bs(arr,x));
        System.out.println(bs(arr,X));
    }

    //we need to find smallest index or smallest number which is larger than x in nums else the size of array
    static int bs(int[] arr,int x){
        int n = arr.length;
        
        int low = 0,high = n-1;
        while(low < high){
            int mid = ( low + high ) / 2;

            //if mid is smaller than or equal to x then its definetly not our answer as we need greater than x , so we exclude this index
            if( arr[mid] <= x){
                low = mid + 1;
            }
            //now mid of arr is greater than x,hence its a potential answer so we include it,while searching
            else{
                high = mid;
            }
        }
        
        return arr[high] > x ? high : n ;
    }

    //this implementation is standard and more readable & simple
    static int bs(int[] arr,Integer x){
        int n = arr.length;
        int ans = n;

        int low = 0,high = n-1;
        //low <= high is standard and we follow it here too unlike above one,if we dont we miss cases and if we do in above one it goes to infinite loop due to high = mid statement
        while(low <= high){
            int mid = ( low + high ) / 2;

            //if mid is smaller than or equal to x then its definetly not our answer as we need greater than x , so we exclude this index(mid)
            if( arr[mid] <= x){
                low = mid + 1;
            }
            //now mid of arr is greater than x,hence its a potential answer so we update our and and exclude it(mid) too
            else{
                ans  = mid;
                high = mid - 1;
            }
        }
        
        return ans;
    }
}