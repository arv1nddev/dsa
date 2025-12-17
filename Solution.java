import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {5,3,2,4,1};

        System.out.println(brute(arr));
        System.out.println(optimal(arr));
    }
    //brute
    static int brute(int[] arr){
        int n = arr.length;
        int count = 0;

        for(int i  = 0; i < n ; i++){
            for(int j = i+1 ; j < n ; j++){
                if ( arr[i] > arr[j]) count++;
            }
        }
        return count;
    }

    //optimal : notice that if some how by dividing array into two sorted parts we can count no of inversions in linear time
    //
    static int optimal(int[] arr){
        return divide(arr,0,arr.length-1);
    }
    static int divide(int[] arr,int left,int right){
        if(left == right) return 0 ;
        int mid = (left + right) / 2;

        int a = divide(arr,left,mid);
        int b = divide(arr,mid+1,right);

        return a + b + conquer(arr,left,mid,right);
    }
    static int conquer(int[] arr,int left,int mid,int right){
        int count = 0;
        int[] temp = new int[right-left+1];

        int low = left;
        int high = mid+1;
        int index = 0;

        while(low <= mid && high <= right){
            if(arr[low] <= arr[high]){
                temp[index++] = arr[low++];
            }else{
                temp[index++] = arr[high++];
                count += mid - low + 1;
            }
        }

        while(low <= mid) temp[index++] = arr[low++];
        while(high <= right) temp[index++] = arr[high++];

        index = 0;
        for(int i = left ; i <= right ; i++) arr[i] = temp[index++];

        return count;
    }
}