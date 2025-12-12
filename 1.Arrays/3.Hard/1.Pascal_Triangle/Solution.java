import java.util.*;
class Solution{
    public static void main(String[] args) {

        System.out.println(element(7,5));

        System.out.println(row(7).toString());

        List<List<Integer>> triangle = generate(7);
        for(List<Integer> layer : triangle) System.out.println(layer.toString());
    }

    //value of element at row r  and column c is r-1 C c-1 or ncr if n = r-1 and r = c-1;
    static int element(int row,int col){
        int  n = row-1;   //why do we minus though, is 0 based indexing just natural?
        int r = col-1;
        r = Math.min(r,n-r);

        int val = 1;
        for(int i =0;i<r;i++){
            val *= n-i;
            val /= (i+1);
        }
        return val;
    }

    //now instead of getting elements from column 1 to n (as there are n columns/elements in nth row) through calling element again and again
    //we do some cleverery by a general obesrvation,here see for your self
    static List<Integer> row(int n){
        List<Integer> layer = new ArrayList<>();
        layer.add(1);

        int val = 1;
        for(int i = 1;i<n;i++){
            val *= n-i;
            val /= i;
            layer.add(val);
        }

        return layer;
    }

    //to generate pascal triangle we can use formula but that would be inefficient in tc,we use the fact or formula that 
    //for a layer n ,element at index i is equal to sum of elements at index i-1 and index i in upperlayer/prevlayer
    static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<>();
        pascal.add(new ArrayList<>(Arrays.asList(1)));

        for(int i =1;i<numRows;i++){

            List<Integer> layer = new ArrayList<>();
            layer.add(1);

            List<Integer> prev = pascal.get(i-1);
            for(int j = 1;j<i;j++){
                int num = prev.get(j-1) + prev.get(j);
                layer.add(num);
            }

            layer.add(1);
            pascal.add(layer);
        }
        return pascal;
    }
}