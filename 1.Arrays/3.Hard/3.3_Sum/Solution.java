import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {-1,0,1,2,-1,-4};

        // for(List<Integer> l : brute(arr)) System.out.println(l);
        // System.out.println();

        // for(List<Integer> l : better2(arr)) System.out.println(l);
        // System.out.println();

        // for(List<Integer> l : better3(arr)) System.out.println(l);
        // System.out.println();

        for(List<Integer> l : optimal(arr)) System.out.println(l);
        System.out.println();
    }

    //brute : we try every possible triplet and check their sum
    static List<List<Integer>> brute(int [] arr){
        int n = arr.length;
        Set<List<Integer>> set = new HashSet<>();
        
        for(int i = 0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k = j+1;k<n;k++){
                    if (arr[i]+arr[j]+arr[k] == 0){
                        List<Integer> ts = Arrays.asList(new Integer[]{arr[i],arr[j],arr[k]});
                        Collections.sort(ts);
                        set.add(ts);
                    }
                }
            }
        }

        List<List<Integer>> triplets = new ArrayList<>();
        for(List<Integer> triplet : set) triplets.add(triplet);

        return triplets;
    }
    //tc = O(n3),sc=O(1) keeping aside O(n) used for answer
    


    
    //we use what we learned in 2 sum,if want the sum 0 ,i need to have  sum of a negative value of current any other two elements,and
    // again what better than a hashmap to remember values , both where(indices) and what
    //arr[i] + arr[j]  + arr[k] = 0
    //arr[i] + arr[j]  = - arr[k] 

    static List<List<Integer>>  better(int [] arr){
        int n = arr.length;
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer,Integer[]> sums = new HashMap<>(); 
        
        for(int i = 0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int sum = arr[i] + arr[j];
                sums.put(sum,new Integer[]{i,j});
            }
        }
        for(int i = 0;i<n;i++){
            if(sums.containsKey(-arr[i])){
                Integer[] pair = sums.get(-arr[i]);
                if(pair[0] != i && pair[1] != i) {
                    List<Integer> list = Arrays.asList(new Integer[]{arr[i],arr[pair[0]],arr[pair[1]]});
                    Collections.sort(list);
                    set.add(list);
                }
            }
        }
        
        List<List<Integer>> triplets = new ArrayList<>();
        for(List<Integer> triplet : set) triplets.add(triplet);
        
        return triplets;
    }
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //above method doesnt work becuase of a fundamentally wrong reason 
    //it overwrites the same sum as key with different index,discarding valid triplet
    //eg = -3,9 => sum 6 -> [0,8]
    //now at  34,-28 => sum 6 -> [7,9]
    //to tackle that i modified it like this,now i store list of valid pairs

    static List<List<Integer>> better2(int[] nums) {
        int n = nums.length;
        Map<Integer,List<Integer[]>> needs = new HashMap<>();
        for(int i = 0;i<n;i++){
            for(int j = i+1;j<n;j++){
                int sum = nums[i] + nums[j];
                List<Integer[]> pairs = needs.computeIfAbsent(sum,k -> new ArrayList<>());
                pairs.add(new Integer[]{i,j});
            }
        }

        Set<List<Integer>> set = new HashSet<>();
        for(int i =0 ;i<n;i++){
            int need = -nums[i];
            if(needs.containsKey(need)){
                List<Integer[]> pairs= needs.get(need);
                for(Integer[] pair : pairs){
                    if(pair[0] != i && pair[1] != i){
                    List<Integer> triplet = Arrays.asList(new  Integer[]{nums[pair[0]],nums[pair[1]],nums[i]});
                    Collections.sort(triplet);
                    set.add(triplet);
                }
                }
            }
        }

        List<List<Integer>> triplets = new ArrayList<>();
        for(List<Integer> triplet : set) triplets.add(triplet);

        return triplets;
    }
    //tc = O(n2+n),sc = O(n)
    
    //better  2.0 (optimized to reduce a loop by using set and a lil different approach)
    static List<List<Integer>>  better3(int [] arr){
        int n = arr.length;
        Set<List<Integer>> set = new HashSet<>();
        
        for(int i = 0;i<n;i++){
            Set<Integer> needs = new HashSet<>();
            for(int j=i+1;j<n;j++){
                int need = -(arr[i] + arr[j]);
                if(needs.contains(need)){
                    List<Integer> triplet = Arrays.asList(new Integer[]{arr[i],need,arr[j]});
                    Collections.sort(triplet);
                    set.add(triplet);
                }
                needs.add(arr[j]);
            }
        }
        List<List<Integer>> triplets = new ArrayList<>();
        for(List<Integer> triplet : set) triplets.add(triplet);
        
        return triplets;
        }
        //tc = O(n2),sc = O(n)

    //optimal : n2 is not acceptable we need to do better by O(n)
    //optimal : we are using external data structure to store unique triplets which is O(n),we will optimize it
    //instead of sortig triplets again and agian we sort the whole array for once and dont use/consider same elements(equal)
    //we use two pointers left and right in each itereation to reduce tc 

    static List<List<Integer>>  optimal(int [] arr){
        int n = arr.length;
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();

        for(int i = 0;i<n;i++){
            if(i>0 && arr[i]==arr[i-1]) continue;

            int left = i+1;
            int right = n-1;
            while(left<right){
                int sum = arr[i] + arr[left] + arr[right];
                if(sum==0){

                    triplets.add(Arrays.asList(arr[i],arr[left],arr[right]));
                    //increment left and right as we want unique triplets only
                    right--;
                    left++;
                    //if values dont change keep increasing and decreasing left and right poitner till we find new values
                    while(left<right && arr[right] == arr[right+1]) right--;
                    while(left<right && arr[left] == arr[left-1]) left++;

                }else if(sum<0){
                    //hey my sum is neagtive i need to increment it to make it zero so please left poitner go to right side to increase sum
                    left++;
                    //skip same value as it wont change the sum and this if condition will trigger again indirectly forming loop
                    while(left<right && arr[left] == arr[left-1]) left++;
                }else {
                    //hey my sum is positive i need to decrement it to make it zero so right pointer please go to left side
                    right--;
                    //skip same values (optional as condition wont change if sum will not change ,indirectly doing what while loop does)
                    while(left<right && arr[right] == arr[right+1]) right--;

                }
            }
        }
        return triplets;
    }
    
}