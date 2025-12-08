import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {2,2,3,3,1,2,2};

        System.out.println(majority(arr,true));
    }

    //brute can be counting occurences of all elements in array by using two for loop,very inefficient
    //tc = O(n2)

    //better : if range of arr[i] is less,array can be used too for hashing
    static int majority(int[] arr,boolean forPolymorhism){
        int n = arr.length;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i =0;i<n;i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
            // map.put(arr[i],map.computeIfAbsent(arr[i],k -> 0)+1);
        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue() > n/2) return entry.getKey();
        }

        return -1;
    }
    //tc = O(n),sc = O(n)


    //         this 7 remains after canceling (votes--) all other elements,ms va in short
    //           \/
    // 7,7,7,7,7,7,5,5,5,2,2
    // 5,5,5,2,2 

    //optimal : moores voiting algorithm
    static int majority(int[] arr){
        int n = arr.length;
        int candidate = -1;
        int votes = 0;
        
        for(int num : arr){
            if(votes == 0 && candidate != num){
                candidate = num;
            }
            if(candidate != num){
                votes--;
            }else votes ++;
        }

        //remember vote represent nothing here,if votes > 0 then majority element might exist but its not guaranteed
        // eg : 7,7,7,5,5,5,1,1
        // in the end : candidate = 1 and votes = 2,but 1 is not majority element

        //this happens only if there is no majority element
        
        int count =0;
        for(int num : arr) if (num==candidate) count++;

        return count > n/2 ? candidate : -1;
    }
    //tc = O(n),sc = O(1)
}