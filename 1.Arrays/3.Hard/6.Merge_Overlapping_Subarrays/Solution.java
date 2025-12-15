import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[][] arr = new int[][] {{1,3},{2,6},{8,9},{9,11},{8,10},{2,4},{15,18},{16,17}};
        System.out.println(Arrays.deepToString(optimal(arr)));
        System.out.println(Arrays.deepToString(cleaner(arr)));
    }

    // brute : its same as better but we iterate one again to each interavls start and check if its less than last inserted interavals end
    // if yes then continue as its already considered
    //tc = O(nlogn + 2n) ,sc = O(n) extra
    
    // better/optimal : we sort the intervals first by start then for same starts by their end
    // whenever we see two intervals such that i(end) < i+1(start),we make new interval i(start) to max( i(end) , i+1(end) ),then chain them till conditon is valid
    static int[][] optimal(int[][] arr){
        int n = arr.length;

        Arrays.sort(arr,Comparator
        .comparing((int[]a) -> a[0])
        .thenComparing((int[] a) -> a[1])
        );

        //this works too btw and is less verbose
        //also we dont need to compare the end ,as start will always be less and we will choose max of ends anyway
        //eg  1,3   1,9 ,  1,3  ,  1,6   1,2   answer will stil come out => 1,9 
        Arrays.sort(arr,(a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        List<List<Integer>> list = new ArrayList<>();
        int begin = arr[0][0];
        int prevEnd = arr[0][1];
        for(int i = 1;i<n;i++){
            int start = arr[i][0];
            int end = arr[i][1];

            if(start <= prevEnd){
                //this is imp we take the end which is more right or far right,eg [2,8],[3,6] our end should be 8 not 6
                prevEnd = Math.max(end,prevEnd);
                // prevEnd = end ;  wrongggggggggggggggggggg,makes new interval 2,6 in above eg
            }else{
                list.add(Arrays.asList(new Integer[]{begin,prevEnd}));
                begin = start;
                prevEnd = end;
            }
        }
        list.add(Arrays.asList(new Integer[]{begin,prevEnd}));
        
        int m = list.size();
        int[][] answer = new int[m][2];
        for(int i = 0;i<m;i++){
            answer[i][0] = list.get(i).get(0);
            answer[i][1] = list.get(i).get(1);
        }

        return answer;
    }
    //tc = O(nlogn + n),sc = O(n) extra
    
    //this one is not only more cleaner and shorter but quite a genius because of how it uses a reference newInterval to arrays in list to do 
    //inplace chnages in list's array also avoiding the last add statement by this
    static int[][] cleaner(int[][] arr){
        int n = arr.length;
        if(n == 1) return arr;

        Arrays.sort(arr,(a,b) -> a[0] - b[0]);

        List<int[]> list = new ArrayList<>();
        int[] newInterval = arr[0];
        list.add(newInterval);

        for(int[] interval : arr){

            if(interval[0] > newInterval[1]){
                newInterval = interval;
                list.add(newInterval);
            }else{
                newInterval[1] = Math.max(interval[1],newInterval[1]);
            }
        }

        int m = list.size();
        return list.toArray(new int[m][]);
    }
    //tc = O(nlogn + n),sc = O(n) extra
}