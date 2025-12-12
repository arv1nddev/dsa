import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {2,-3,4,-1,-2,1,5,-3};
        System.out.println(mss(arr));

        int[] subarray = mssWithsa(arr);
        System.out.print(subarray[0] + " = ");
        for(int i = subarray[1];i<=subarray[2];i++) if (i<arr.length) System.out.print(arr[i] + " + ");
    }

    //brute 
    static int mss(int [] arr,boolean forPolymorhism){
        int n = arr.length;
        int max = Integer.MIN_VALUE;

        for(int i =0;i<n;i++){
            for(int j=i;j<n;j++){
                int sum =0;
                for(int k = i ;k<=j;k++){
                    sum += arr[k];
                }
                max = Math.max(max,sum);
            }
        }

        return max;
    }
    //tc = O(n3),sc = O(1), nightmare
    
    //better
    static int mss(int [] arr,int forPolymorhism){
        int n = arr.length;
        int max = Integer.MIN_VALUE;

        for(int i =0;i<n;i++){
            int sum =0;
            for(int j=i;j<n;j++){
                sum += arr[j];
                max = Math.max(max,sum);
            }
        }

        return max;
    }
    //tc = O(n2),sc = O(1),still nightmare if n = 10e5


    //optimal : kadane's algorithm
    //simple logic if current value is neagtive,update max and leave it since it will decrease our sum's value;
    static int mss(int [] arr){
        int max = Integer.MIN_VALUE;
        int n = arr.length;

        int sum =0;
        for(int i=0;i<n;i++){

            sum += arr[i];
            max = Math.max(max,sum);

            if(sum<0) sum = 0;
        }
        return max;
    }


    //lets try to get subarray too
    static int[] mssWithsa(int [] arr){
        int max = Integer.MIN_VALUE;
        int n = arr.length;
    
        int sum =0, left = -1,right = -1,lastLeft = -1;
        for(int i=0;i<n;i++){
    
            sum += arr[i];
            if (sum > max){
                left = lastLeft;
                right = i;
                max = sum;
            }
    
            if (sum<0){
                lastLeft = i+1;   //+1 since this index wont be included in our subarray,but then we need to make sure i is not n-1 when printing array
                sum = 0;
            }
        }
        return new int[] {sum,left,right};
    }
    //tc = O(n),sc =O(1)
}