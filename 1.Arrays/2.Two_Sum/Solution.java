import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[] arr = new int[] {5,2,6,13,4,8,16,9};
        int target = 15;

        System.out.println(Arrays.toString(twoSum(arr, target,0)));
    }

    //brute
    static int[] twoSum(int[] arr,int target){
        int n = arr.length;
        int [] indices = new int[2] ;

        for(int i =0;i<n;i++){
            // starting j from 0 is redundant ,it just rechecks checked pairs,but then it still is O(n2)
            for(int j = i+1;j<n;j++){
                if (i==j) continue;
                if (arr[i] + arr[j] == target){
                    indices[0] = i; 
                    indices[1] = j; 
                    return indices;
                }
            }
        }

        return new int[] {-1,-1};
    }
    //tc = O(n2),sc = O(1)
    
    //optimal
    static int[] twoSum(int[] arr,int target,int forPolymorphism){
        int n = arr.length;
        int[] indices = new int[2];
        
        //we remember the index of needed part to make target
        // if we must return index too we can save indices in map or 2d array and then fetch indices ,but its not optimal sc is too much
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<n;i++){
            
            int needed = target - arr[i];
            
            if(map.containsKey(needed)){
                indices[0] = map.get(needed);
                indices[1] = i;
                return indices;
            }
            
            map.put(arr[i],i);
        }
        return new int [] {-1,-1};
    }
    //tc = O(n),sc = O(n)
    
    //if using map is not allowed,we can return numbers not indices
    static int[] twoSum(int[] arr,int target,boolean forPolymorphism){
        int nums[] = new int[2];
        int n = arr.length;
        
        Arrays.sort(arr);
        
        int left =0,right=n-1;
        while(left<right){
            int sum = arr[left] + arr[right];
            if (sum == target){
                nums[0] = arr[left];
                nums[1] = arr[right];
                return nums;
            }
            if (sum < target) left++;
            else right--;
        }
        return new int[] {-1,-1};
    }
    //tc = O(nlogn),sc = O(n)
}
