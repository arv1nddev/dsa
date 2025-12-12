import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                                    new int[] {1,2,3,4,5,6},
                                    new int[] {7,8,9,10,11,12},
                                    new int[] {13,14,15,16,17,18},
                                    new int []{19,20,21,22,23,24}
                                };
                                
        System.out.println(spiral(matrix).toString());
    }

    //there is only one solution which is optimal
    //its very simle and more like simulaiton than logic
    //if you notice the spiral its just rectangles inside rectangles
    //so if somehow we can simulate a clockwise rectangle traversal at a time and update it accordingly we will get our spiral

    //we make four vairables : top,right,bottom,left
    //top denots the topmost layer of rectangle
    //right denoets the right side fo rectangle
    //bottom denotes the bottom side of rectangle
    //left denotes the left side of rectangle

    //we do top++ after traversing the top side/layer since its been traversed
    //then same for right by right--
    //then for bottom by bottom--
    //then for left by left++

    //but when do we stop ?
    //depending upon hieght and breadth of rectangle last shape would be proper rectangle(if h is even and b>1 or b is even and h>1)
    // or a line (if h is odd or b is odd)
    //we stop when there are no rectangles/line left that i.e if top>bottom or left>right

    //implementation
    static List<Integer> spiral(int[][] matrix){

        int height = matrix.length;
        int breadth = matrix[0].length;
        List<Integer> spiral = new ArrayList<>();

        int top = 0,right = breadth-1,bottom = height-1,left = 0;

        while(top<=bottom && left<=right){

            for(int i = left ;i<=right;i++) spiral.add(matrix[top][i]);
            top++;

            for(int i = top;i<=bottom;i++) spiral.add(matrix[i][right]);
            right--;

            if(bottom<top) break;
            for(int i = right ;i>=left;i--) spiral.add(matrix[bottom][i]);
            bottom--;

            if(left>right) break;
            for(int i = bottom;i>=top;i--) spiral.add(matrix[i][left]);
            left++;
        }
        return spiral;
    }
}