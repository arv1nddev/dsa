import java.util.*;
class Solution{
    public static void main(String[] args) {
        int[] prices = new int[] {7,1,5,3,6,4};

        System.out.println(Arrays.toString(brute(prices)));
        System.out.println(Arrays.toString(optimal(prices)));
    }

    //we buy each day and sell each valid day and return max profit with buy sell dates
    static int[] brute(int[] prices){
        int n = prices.length;
        int[] stats = new int[3];
        
        int max = 0,bought = -1,sold = -1;
        for(int buy = 0;buy<n;buy++){
            for(int sell = buy+1;sell<n;sell++){

                int profit = prices[sell]-prices[buy];

                if(profit>max){
                    max=profit;
                    bought = buy;
                    sold = sell;
                }

            }
        }

        stats[0] = max;
        stats[1] = bought;
        stats[2] = sold;

        return stats;
    }
    //tc = O(n2),sc=const

    //we use the fact that stock can be sold only after buying or we cant sell before buying
    //we simply buy on day when prices is minimum till selling day and then sell wehn profit is max
    //or keep updating buy to minimum as it will increase profit and keep updating sell to maximum as it too will inc profit
    static int[] optimal(int[] prices){
        int n = prices.length;
        int[] stats = new int[3];

        int max = 0,bought =0,sold = 0;
        for(int day = 0;day<n;day++){

            if(prices[day]<prices[bought]) bought = day; //no use of checking selliing today with previous bought day as its bound to be negative
            //we only check if todays selling price is greater than bought price
            else if(prices[day] != prices[bought]){
                int profit = prices[day]-prices[bought];
                if(profit>max){
                    max = profit;
                    sold = day;
                }
            }
        }

        stats[0] = max;
        stats[1] = bought;
        stats[2] = sold;
        return stats;
    }
}