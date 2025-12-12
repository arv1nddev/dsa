import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,-3,1,1,1,4,2,-3};
        int k = 3;

        System.out.println(brute(arr,k));
        System.out.println(better(arr,k));
        System.out.println(optimal(arr,k));
    }

    //brute : we generate all subarrays then do the sum of each subarray if its equal to k we increment count;
    static int brute(int[] arr,int k){
        int n = arr.length;
        int count = 0;

        for(int i = 0;i<n;i++){
            for(int j = i;j<n;j++){
                int sum = 0;
                for(int idx = i;idx <= j;idx++){
                    sum += arr[idx];
                }
                if(sum == k) count++;
            }
        }
        return count;
    }
    //tc = O(n3),sc = const

    //better : we keep a varaible sum which tracks sum from i to j ,avoiding loopoing again for sum of subarray
    static int better(int[] arr,int k){
        int n = arr.length;
        int count = 0;

        for(int i = 0;i<n;i++){
            int sum = 0;
            for(int j = i;j<n;j++){
                sum += arr[j];
                if(sum == k) count++;
            }
        }
        return count;
    }
    //tc = O(n2),sc = const

    //optimal : we iterate through array with vaiable sum which keeps sum of array from index 0 to i
    //now at a index i ,we observe that for a subarray[j,i] to have sum k,where 0<=j<=i ,
    //there must be a subarray [idx,j-1] with its sum = (sum - k) such that -1<=idx<=j-1 ,-1 is to indicate subarray with no elements,j=0 & sum=0
    //subarray [idx,j-1] => (sum - k) + subarray[j,i] => (k)  = subarray[0,i] => sum
    //and what better than a hashmap to remember existence/frequencies of sums

    static int optimal(int[] arr,int k){
        int n = arr.length;
        int count = 0;

        Map<Integer,Integer> sums = new HashMap<>();
        sums.put(0,1); //the case where we select the subarray[0,i] or idx = -1
        int sum = 0;

        for(int i = 0;i<n;i++){
            sum += arr[i];
            if(sums.containsKey(sum-k)) count += sums.get(sum-k);
            sums.put(sum,sums.getOrDefault(sum,0)+1);
        }

        return count;
    }
    //tc = O(n),sc = O(n)
}