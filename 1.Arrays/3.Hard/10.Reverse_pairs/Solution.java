import java.util.*;
class Solution{
    public static void main(String[] args) {
        // int[] arr = new int[] {4,13,6,2,1,3,21,1,3,25,5,9,4};
        int[] arr = new int[] {40,25,19,12,9,6,2};

        System.out.println(Arrays.toString(arr));
        System.out.println(optimal(arr));
    }
    //brute : gnereate all pairs i < j such and check if arr[i] > 2 * arr[j] ,if yes count++

    //optimal : here too ,notice that if some how by dividing array into two sorted parts at a cut we can count no of paris in linear time
    //if an element Li in left part/array is greater than an element Rj * 2 in right array,then all elements after Li are bound to make pairs
    //with Rj i.e no of pairs contibuted by Li is (n-1) - i + 1 = n - i (see line 38)

    static int optimal(int[] arr){
        return divide(arr,0,arr.length-1);
    }
    static int divide(int[] arr,int left,int right){
        if (left == right) return 0 ; 

        int mid = ( left + right ) / 2;
        int a = divide(arr, left, mid);
        int b = divide(arr, mid + 1, right);
        return a + b + conquer(arr,left,mid,right);
    }

    static int conquer(int[] arr,int left,int mid,int right){
        int count = 0;
        int[] temp = new int[right - left + 1];

        int index = 0;
        int low = left ;
        int high = mid + 1;

        while(low<=mid && high <= right){
            if(arr[low] > arr[high] * 2){
                count += mid - low + 1;
                high++;
            }else{
                low++;
            }
        }

        low = left;
        high = mid + 1;

        while(low <= mid && high <= right){
            if(arr[low] <= arr[high]) temp[index++] = arr[low++];
            else temp[index++] = arr[high++];
        }

        while(low <= mid) temp[index++] = arr[low++];
        while(high <= right) temp[index++] = arr[high++];

        for(int i = 0;i <= right-left ;i++) arr[i + left] = temp[i];
        return count;
    }
}


















// dryyyyyyyyyyyyyyyyyyyyyyyyyyy runnnnnnnnnnnnnn theeeeeeeeeeeeeeeeeeee  problemmmmmmmmmmmm opennnnnnnnnnnn innnnnnnn window