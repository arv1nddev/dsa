import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {2,3,-2,4};

        System.out.println(optimal(arr));
    }

    //brute : generate all subarrays find product of elements of subarray
    //tc = O(n3),sc = const
    
    //better : while generating subarrays keep track of product of elments for inner loop using a variable
    //tc = O(n2),sc = const

    //better : if we notice the product will only decrease if its negative (leaving 0 aside),and its only negative if we have odd no of zeroes
    //if all number are +ve or there are even no of -ves then the whole array is the subarray,so we just need to take care of odd -ves
    //now to make odd -> even we have to leave a single -ve number and that can be simulated using prefix and suffix
    //we start from left side with preifx and keep updating it,just before last(rightmost) -ve number, we would have our largest sum leaving this -ve number
    //then we do the same from right side,and we would have our largest sum leaving leftmost -ve number
    //0 is a special case,taking it will always make our product zero so we kinda skip it,and start afresh from next number,this strategy ensures we always get maximum subarray product
    static int optimal(int[] nums){
        int n = nums.length;
        int max = Integer.MIN_VALUE;

        int prefix = 1 ;
        for(int i = 0 ; i < n ; i++){
            prefix *= nums[i];
            max = Math.max(max,prefix);
            if(prefix == 0) prefix = 1;
        }

        int suffix = 1 ;
        for(int i = 0 ; i < n ; i++){
            suffix *= nums[i];
            max = Math.max(max,suffix);
            if(suffix == 0) suffix = 1;
        }

        return max;
    }
}