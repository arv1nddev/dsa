import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr1 = new int[] {1,3,5,7};
        int[] arr2 = new int[] {0,2,6,8,9};

        merge1(arr1,arr2);
        // merge2(arr1,arr2);
        System.out.println(Arrays.toString(arr1) + Arrays.toString(arr2));
    }

    //better1 : we know arrays are sorted so ,we find the largest element of arr1 and smallest element of arr2,
    //now the largest element of arr1 must smaller than or equal to the smallest element of arr2 for both to be sorted as whole,so we make them
    static void merge1(int[] arr1,int[] arr2){
        int m = arr1.length;
        int n = arr2.length;

        int left = m-1;
        int right = 0;

        while(left>=0 && right<n){
            if (arr1[left] > arr2[right]){
                swap(arr1,left,arr2,right);
                left--;
                right++;
            }else break;
            //once the largest value in arr1 is smaller than smallest value in arr2 ,it is meaningless to check further smaller values in arr1
            //and larger values in arr2 since it will always be false i.e smaller than largest in arr2 < larger than smallest in arr2,always
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }
    //tc = O( min(m,n) + mlogm + nlogn),sc = const

    //better2 : we use the gap method to sort the array ,you know the shell sort
    static void merge2(int[] arr1,int[] arr2){
        int m = arr1.length;
        int n = arr2.length;

        
    }
    //tc = O( (m+n)log(m+n)) ,sc = const

    //optimal : very simple,we take largest element of nums1 and nums2 and place it in our virtual array;
    //since we cant overwrite the front we start by end of our virtual array,which is filled with zero
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n -1;
        int k = m + n - 1;

        //place largest of nums1 and nums2 at  k
        while(k >= 0){
            if ( i >= 0 && (j < 0 || nums1[i] >= nums2[j]) ){
                nums1[k] = nums1[i];
                i--;
            }else{
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }
    //tc = O(n+m) , sc = O(1)

    static void swap(int[] arr1,int left,int[] arr2,int right){
        int temp = arr1[left];
        arr1[left] = arr2[right];
        arr2[right] = temp;
    }
}