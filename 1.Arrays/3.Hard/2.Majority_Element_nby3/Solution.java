import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {1,1,1,1,3,2,2};

        //ans = {size,num1,num2};
        System.out.println(Arrays.toString(brute(arr)));
        System.out.println(Arrays.toString(better(arr)));
        System.out.println(Arrays.toString(optimal(arr)));
    }

    //brute : we iterate through each element of array and count each elements freq
    static int[] brute(int[] arr){
        int n = arr.length;
        int[] ans = new int[3];

        for(int element : arr){
            //it also handles the case when 0 is also a element in arr,else we can use if(ans[0]==0 || ans[0] != element),as ans[0]=0,initially
            //well no its redundant as if ans[0]==0 is false ans[0]==1 automatically becomes true , i removed it in better
            if(ans[0]==0 || (ans[0] ==1 && ans[1] != element)){
                int freq = 0;
                for(int elm : arr){
                    if(elm == element) freq++;
                }
                if(freq>n/3){
                    ans[0]++;
                    ans[ans[0]]=element;
                    if(ans[0]==2) break;
                }
            }
        }
        return ans ;
    }
    //tc = O(n2),sc = O(3),const

    //better : we use hashmap to keep track of the freq of each element 
    static int[] better(int[] arr){
        int n = arr.length;
        int[] ans = new int[3];

        Map<Integer,Integer> freq = new HashMap<>();
        for(int element : arr){
            //update
            freq.put(element,freq.getOrDefault(element,0)+1);
            //check
            if(( ans[0] ==0  || ans[1]!= element) && freq.get(element) > n/3){
                ans[0]++;
                ans[ans[0]] = element;
                if(ans[0]==2) break;
            }
        }
        return ans ;
    }
    //tc = O(n),sc = O(n)

    //optimal : we try to replicate voye moree algo for two elements/candidate here to reduce sc-> O(1)
    //pseudo code of vm algo: for(int i = 0;i<n;i++){
                            //   if(vote/count ==0){
                            //   candidate/element = nums[i];
                            //   vote/count = 1;
                            //   } else if(candidate/element == nums[i]) vote/count++;
                            //   else vote/count-- ;
                            // }

    static int[] optimal(int[] arr){
        int n = arr.length;
        int[] ans = new int[3];

        int elm1 = Integer.MIN_VALUE,elm2 = Integer.MIN_VALUE;
        int count1 = 0,count2 = 0;
        for(int element : arr){
            if(count1 == 0){
                elm1 = element;
                count1++;
            }else if(count2 == 0){
                elm2 = element;
                count2++;
            }else if(element == elm1) count1++;
            else if(element == elm2) count2++;
            else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        for(int elm : arr) if(elm == elm1) count1++;
        if(count1>n/3){
            ans[0]++;
            ans[ans[0]]=elm1;
        }

        count2=0;
        for(int elm : arr) if(elm == elm2) count2++;
        if(count2>n/3){
            ans[0]++;
            ans[ans[0]]=elm2;
        }

        return ans ;
    }
    
    //well well turns out replicating without logic results in rejection i mean wrong answer here
    //we missed the cases where count1 is 0 and element = elm2 ,it should go to #case 4 and increment count2 but we make elm1 = em2,
    //now same element is fighting for two places not good,same when count2 = 0 and element = elm1 it should go to #case 3 i.e count1++
    //to handle this cases we update our conditions
    
    static int[] optimalr(int[] arr){
        int n = arr.length;
        int[] ans = new int[3];

        int elm1 = Integer.MIN_VALUE,elm2 = Integer.MIN_VALUE;
        int count1 = 0,count2 = 0;
        for(int element : arr){
            if(count1 == 0){
                elm1 = element;
                count1++;
            }else if(count2 == 0){
                elm2 = element;
                count2++;
            }else if(element == elm1) count1++;
            else if(element == elm2) count2++;
            else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        for(int elm : arr) if(elm == elm1) count1++;
        if(count1>n/3){
            ans[0]++;
            ans[ans[0]]=elm1;
        }

        count2=0;
        for(int elm : arr) if(elm == elm2) count2++;
        if(count2>n/3){
            ans[0]++;
            ans[ans[0]]=elm2;
        }
        
        return ans ;
    }
    //tc = O(n),sc = O(1)
}