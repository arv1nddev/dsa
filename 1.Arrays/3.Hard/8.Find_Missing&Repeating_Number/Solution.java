import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] arr = new int[] {7,3,5,1,2,4,4};

        System.out.println(Arrays.toString(brute(arr)));
        System.out.println(Arrays.toString(better(arr)));
        System.out.println(Arrays.toString(optimal1(arr)));
        System.out.println(Arrays.toString(optimal2(arr)));
    }

    //brute : we connt the freq of each num using a loop each time if 2 its repeating one if its zero its missing one
    static int[] brute(int[] nums){
        int[] rnm = new int[2];
        int n = nums.length;
        int missing  = -1;
        int repeating  = -1;
        
        for(int i = 1;i<=n;i++){
            int count  = 0;
            for(int j = 0;j<n;j++){
                if(nums[j] == i) count++;
            }
            if(count == 0) missing = i;
            if(count == 2) repeating = i;
            if(missing != -1 && repeating != -1) break;
        }
        
        rnm[0] = repeating;
        rnm[1] = missing;

        return rnm;
    }
    //tc = O(n2),sc = const

    //better : we use a hashmap(array) to track freqs
    static int[] better(int[] nums){
        int n = nums.length;
        int[] rnm = new int[2];
        int[] freq = new int[n+1];

        for(int i = 0;i<n;i++) freq[nums[i]]++;

        int missing  = -1;
        int repeating  = -1;

        for(int i = 1;i<=n;i++){
            if(freq[i] == 0) missing = i;
            else if(freq[i] == 2) repeating = i;
        }

        rnm[0] = repeating;
        rnm[1] = missing;

        return rnm;
    }
    //tc = O(2n),sc = O(n)

    //optimal1 : we use basic maths to get repeating and misisng values
    static int[] optimal1(int[] nums){
        int n = nums.length;
        
        //sum = 1+2+3+..+n
        long sumN = n*(n+1);
        sumN /= 2;
        //sum2 = 1^2 + 2^2 + 3^2 +....+n^2
        long sumN2 = n*(n+1)*(2*n+1);
        sumN2 /= 6; 

        //sum with repeaing and missing value
        long sum  = 0;
        long sum2 = 0;
        for(int num : nums) {
            sum += num;
            sum2 += num*num;
        }

        // 1,2,3,4,4,6, - 1,2,3,4,5,6
        long x = sum - sumN;   //x = repeating - missing
        long sq = sum2 - sumN2; //sq = r^2 - m^2;
        long y = sq/x;  // y = repeatin + missing

        int repeating = (int)(x+y)/2;
        int missing = (int)(y-x)/2;
                
        int[] rnm = new int[2];
        rnm[0] = repeating;
        rnm[1] = missing;

        return rnm;
    }
    //tc = O(n),sc = const

    //optimal2 : we use xor to get repeating and misisng values
    static int[] optimal2(int[] nums){
        int n = nums.length;
        int[] rnm = new int[2];

        //we take xor of 1^2^...^n and nums[0]^nums[1]....^nums[n-1],expect repeating and missing all other becmoes zero,and repeaing two also becomes zero
        //so xor becomes repeating ^ missing
        int xor = 0 ;
        for( int i = 1;i<=n;i++){
            xor ^= (nums[i-1] ^ i);
        }

        //the first set bit(ith bit) in xor is the first differentiating bit between repeating and missing ,i mean thats what xor is 1 at diff bits
        //now by dividing xor in two groups , bit i = 0 and bit i = 1,and taking xor of all in group,only odd number of nums will remain
        //i.e repeating value(3 times) and missing value(1 time)

        // int mask = 1;
        // while((mask & xor) == 0){
        //     mask <<= 1;
        // }
        int mask = xor & ~(xor - 1);

        int bit0 = 0;
        int bit1 = 0;
        for(int i = 1; i <= n; i++){

            //group with flag bit = 1;
            if( (i & mask) != 0 ) bit1 ^= i;         //and == 1 is wrong since its only 1 when flag is 1,rather check if its == 0  or != 0
            //groups with flag bit 0;                //eg flag = 8,i/num = 8 then flag & i/num = 8 != 0 ,not == 1,for that you have to right shift 
            else bit0 ^= i;                          //num/i and then do & 1 which gives 0 or 1 => i/num >> shift == 1

            if( (nums[i-1] & mask) != 0) bit1 ^= nums[i-1];
            else bit0 ^= nums[i-1];
        }

        //now either of bit0 and bit1 have repeating or missing value,but how do we find out which one is which one
        //quite simple just xor any one with nums if it becomes zero at any point its repaitng else missing,you can do == too but bits are fun
        for(int num : nums){
            if((num ^ bit0) == 0){
                rnm[0] = bit0;
                rnm[1] = bit1;
                return rnm;
            }
        }
        rnm[0] = bit1;
        rnm[1] = bit0;

        return rnm;
    }
    //tc = O(1),sc = const
}