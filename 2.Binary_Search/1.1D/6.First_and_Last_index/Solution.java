import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {5,7,7,8,8,10};
        int target = 8;
        Integer t = 8;

        System.out.println(Arrays.toString(bs(arr,target)));
        System.out.println(Arrays.toString(bs(arr,target,true)));
        System.out.println(Arrays.toString(bs(arr,target,'p')));
    }

    //naive binary appraoch,good but tc  might go to O(n)
    static int[] bs(int[] arr,int target){
        int n = arr.length;
        
        int low = 0,high = n-1;
        while(low <= high){
            int mid = (low + high) / 2;

            if(arr[mid] == target){

                int start = mid,end = mid;
                while(start >= 0 && arr[start] == target) start--;
                while(end <= n-1 && arr[end] == target) end++;

                return new int[]{start+1,end-1};

            }else if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return new int[]{-1,-1};
    }

    //we use lower and upper bound (lower bound is our first index and uppper bound - 1 is our last index and we handle edge cases)
    static int[] bs(int[] arr,int target,boolean forPolyporphism){
        int n = arr.length;
        int[] ans = new int[] {-1,-1};

        int lb = -1;
        int low = 0,high = n - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] >= target){
                lb = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        if(lb == - 1 || arr[lb] != target ) return ans;

        int ub = n;
        low = 0;
        high = n - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] > target){
                ub = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        if(arr[ub - 1] != target ) return ans;
        return new int [] {lb,ub-1};
    }
    //tc = O(2logn)

    //a simple binary search implementation where we reduce search space based on requirement of first and last index
    static int[] bs(int[] arr,int target ,char forPolyporphism) {
        int n = arr.length;

        int low = 0,high = n - 1;
        int first = -1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
                if(arr[mid] == target) first = mid;
            }
        }
        if(first == -1) return new int[] {-1,-1};

        int last = -1;
        low = 0;
        high = n - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] <= target){
                low = mid + 1;
                if(arr[mid] == target);
                last = mid;
            }else high = mid - 1;
        }

        return new int[] {first,last};
    }
}