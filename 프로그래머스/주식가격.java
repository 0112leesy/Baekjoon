import java.util.*;

class Solution {
    public int[] solution(int[] prices) {

        Stack<Integer[]> stack = new Stack<>();
        int[] answer = new int[prices.length];

        for(int i=0; i<prices.length; i++) {
            int currentPrice = prices[i];
            int currentTime = i;

            if(stack.isEmpty() || stack.peek()[1] <= currentPrice) {
                stack.push(new Integer[]{currentTime, currentPrice});
                continue;
            }

            if(stack.peek()[1] > currentPrice) {
                int cnt = 0;
                while(!stack.isEmpty() && stack.peek()[1] > currentPrice) {
                    cnt += 1;
                    Integer[] tmp = stack.pop();
                    answer[tmp[0]] = currentTime - tmp[0];
                }
                stack.push(new Integer[]{currentTime, currentPrice});
            }
        }

        while(!stack.isEmpty()) {
            Integer[] tmp = stack.pop();
            answer[tmp[0]] = prices.length-1 - tmp[0];
        }

        return answer;
    }
}