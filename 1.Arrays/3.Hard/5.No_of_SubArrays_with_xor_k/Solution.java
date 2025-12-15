import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {4,2,2,6,4};
        int target = 0;

        System.out.println(brute(arr,target));
        System.out.println(better(arr,target));
        System.out.println(optimal(arr,target));
    }

    //brute : we generate all subarrays and check if xor is k
    static int brute(int[] arr,int target){
        int n = arr.length;
        int count =0;
        for(int i = 0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int xor = 0;
                for(int k = i;k<=j;k++) xor ^= arr[k];
                if(xor == target) count++;
            }
        }
        return count;
    }
    //tc = O(n3),sc = O(1)
    
    //better : we avoid using third loop reducing tc to n sq
    static int better(int[] arr,int target){
        int n = arr.length;
        int count =0;
        for(int i = 0;i<n;i++){
            int xor = arr[i];
            for(int j=i+1;j<n;j++){
                xor ^= arr[j];
                if(xor == target) count++;
            }
        }
        return count;
    }
    //tc = O(n2),sc = O(1)

    //optimal : remember the subarray with sum k it is same thing served with mask of xor,
    //we need an subarray with xor k ,say the xor of subarray from index 0 to k X ,and there id subarray from index j to i with xor k then
    //there must be subarray from index 0 to j such that its xor is X ^ k
    // xor[0,j] ^ xor[j,k] = xor[0,k]
    // xor[0,j] ^ target = X
    // xor[0,j] = X ^ target
    //so the question boils down  to is there a subarray starting from 0 to j with xor = X^target and what better than map to remember it and
    //its frequencies as set wont remember the fequencies but if there are four subarrays with needed xor then count increases to four times
    static int optimal(int[] arr,int target){
        int n = arr.length;
        int count =0;

        Map<Integer,Integer> needs = new HashMap<>();
        int xor = 0;
        needs.put(0,1);
        for(int i =0;i<n;i++){

            xor ^= arr[i];
            int need = xor ^ target;
            if(needs.containsKey(need)) count += needs.get(need);

            needs.put(xor,needs.getOrDefault(xor,0)+1);
        }

        return count;
    }
    //tc = O(n),sc = O(n)
}