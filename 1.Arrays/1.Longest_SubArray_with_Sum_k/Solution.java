import java.util.*;
class Solution {
    public static void main(String[] args) {
        int[] nums = new int[]{10, 5, 2, 7, 1, 9};
        int k=15;
        int len = longestSubarray(nums,k);
        System.out.println(len);
    }
    public static int longestSubarray(int[] nums, int k) {

        
        // int sum = 0;
        // for (int num:nums) sum += num;
        // return findLength(nums,0,nums.length-1,sum,k);  //brute

        // return findLength(nums, k);                     //better
        // return findLengthAndPrintArray(nums, k);        //fancy better

        return findLength(nums, k,0);  //optimal
    }

    
    //brute
    static int findLength(int[] nums,int left,int right,int sum,int k){
        if(right<left) return 0;
        if(sum==k) return right-left+1;
        
        int max1 = findLength(nums,left+1,right,sum-nums[left],k);
        int max2 = findLength(nums,left,right-1,sum-nums[right],k);
        
        return Math.max(max1,max2);
    }

    //better
    public static int findLength(int[] nums, int k) {
       int len = 0,n=nums.length;

        int sum=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i =0;i<n;i++){
            sum += nums[i];
            int needed = sum-k;

            if(map.containsKey(needed)){
                int idx =  map.get(needed);
                len=Math.max(len,i-idx);
            }else map.put(sum,i);
        }
        return len;
    }

    //better
    static int findLengthAndPrintArray(int[] nums,int k){

        int len = 0,n=nums.length;
        int li=-1;
        int ri=li;


        //no need to make a seperate prefixSum array ,we can achieve the same result during iteration

        // int[] prefixSum = new int[n+1];
        // prefixSum[0]=0;
        // for(int i=1;i<=n;i++){
        //     prefixSum[i] = prefixSum[i-1] + nums[i];
        // }

        int sum=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        
        for(int i =0;i<n;i++){
            sum += nums[i];
            int needed = sum-k;

            if(map.containsKey(needed)){
                int idx =  map.get(needed);
                if(i-idx > len){
                    len=i-idx;
                    ri=i;
                    li=idx+1;
                }
            }else map.put(sum,i);  //keep the keepmost index to get longest length
        }

        if(li==ri) return 0;

        for(int i =li;i<=ri;i++){
            System.out.print(nums[i]+" ,");
        }
        System.out.println();
        return len;
    }

    //optimal (works only for array containing positive elements)
    static int findLength(int[] nums,int k,int forPolymorphism){
        int n= nums.length;
        int maxLen=0,sum=0;

        //we maintain a subarray whose sum is < k as soon as sum > k we shift left till sum < k ,if sum==k update maxLen
        int left = 0,right=0;
        while(right<n){

            sum+=nums[right];
            if (sum==k){
                maxLen=Math.max(maxLen,right-left+1);
            }

            //sum>k
            while(sum>k && left<right){
                sum=sum-nums[left];
                left++;
            }
            right++;
        }

        return maxLen;
    }
}