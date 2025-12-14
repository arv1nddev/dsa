import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {1,0,-1,0,-2,2};
        int k = 0;

        for(List<Integer> l : brute(arr,k)) System.out.println(l);
        System.out.println();
        for(List<Integer> l : better(arr,k)) System.out.println(l);
        System.out.println();
        for(List<Integer> l : optimal(arr,k)) System.out.println(l);
        System.out.println();

    }
    
    //brute : we use four loops and check sum each time
    static List<List<Integer>> brute(int [] arr,int target){
        int n = arr.length;
        Set<List<Integer>> set = new HashSet<>();
        
        for(int i = 0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k = j+1;k<n;k++){
                    for(int l = k+1;l<n;l++){
                        if (arr[i]+arr[j]+arr[k]+arr[l] == target){
                            List<Integer> ts = Arrays.asList(new Integer[]{arr[i],arr[j],arr[k],arr[l]});
                            Collections.sort(ts);
                            set.add(ts);
                        }
                    }
                }
            }
        }

        List<List<Integer>> triplets = new ArrayList<>();
        for(List<Integer> triplet : set) triplets.add(triplet);

        return triplets;
    }
    //tc = O(n4),sc=O(n) for set, keeping aside O(n) used for answer


    //better : we use three loops using same concept as in three sum by remembering/storing needed value in set
    static List<List<Integer>> better(int [] arr,int target){
        int n = arr.length;
        Set<List<Integer>> set = new HashSet<>();
        
        for(int i = 0;i<n;i++){
            for(int j=i+1;j<n;j++){
                Set<Integer> needs = new HashSet<>();
                for(int k = j+1;k<n;k++){
                    int need = target-(arr[i]+arr[j]+arr[k]);
                    if (needs.contains(need)){
                        List<Integer> ts = Arrays.asList(new Integer[]{arr[i],arr[j],need,arr[k]});
                        Collections.sort(ts);
                        set.add(ts);
                    }
                    needs.add(arr[k]);
                }
            }
        }
    
        List<List<Integer>> triplets = new ArrayList<>();
        for(List<Integer> triplet : set) triplets.add(triplet);
    
        return triplets;
    }
    //tc = O(n3),sc=O(n) for set, keeping aside O(n) used for answer

    //optimal : we extend the samme concept used in three sum to four sum which is we use two loops which fixs two indexes i,j then we use two
    //pointers to traverse reamaining indices to find a valid quadruplet
    static List<List<Integer>> optimal(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> quadruplets  = new ArrayList<>();

        for(int i = 0;i<n;i++){
            if(i>0 && nums[i]==nums[i-1]) continue;

            for(int j = i+1;j<n;j++){
                //we use the condition j>i+1 not j>1 which you might think becuase of symmetry but reality is we use j>i+1 ,reason?
                //consider the case,target = 17,nums = 5,7,7,7,...,1...,2 when i = 1(7) and j = 2 (7) ,and nums[left] = 1,nums[right] = 3
                // it checks is j>1 yes ,and then is nums[j]==nums[j+1] and yes ,then the increment(continue) (2 times btw as next is 7 too)
                //and boom we missed a valid combination,why? becuase nums[i] and nums[j] are allowed to be same,condition is same qudraplets are not allowed
                //i.e after this if nums[j+1] == nums[j] ,we should skip it else it will again satisfy the condition and it wont be unique
                //like sum(7,7,1,2)==target and unique but (7,7,1,2) not unique skip the j
                if(j>i+1 && nums[j] == nums[j-1]) continue;

                int left = j+1;
                int right = n-1;
                while(left<right){
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target){
                        quadruplets.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        right--;
                        left++;
                        while(left<right && nums[left]==nums[left-1]) left++;
                        while(left<right && nums[right]==nums[right+1]) right--;
                    }else if(sum > target){
                        //reduce the sum
                        right--;
                    }else left++;
                }
            }
        }
        return quadruplets;
    }
}