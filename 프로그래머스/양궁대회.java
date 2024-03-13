import java.util.*;

class Solution {

    static boolean[] achieved;
    static int[] required;
    static int limit;
    static int max;
    static int[] ans;

    public int[] solution(int n, int[] info) {
        achieved = new boolean[11];
        required = new int[11];
        int initScore = 0;
        for(int i=0; i<info.length; i++) {
            required[i] = info[i] + 1;
        }
        limit = n;
        max = 0;
        ans = new int[11];

        backtracking(10, 0, info);
        if(max == 0) {
            return new int[]{-1};
        }

        return ans;
    }

    static void backtracking(int x, int used, int[] info) { // index 10 -> 0까지
        if(x == -1) {
            int score = countScore(info);
            if(score > max && score > 0) {
                max = score;
                setAns(limit-used);
            }
            return;
        }

        if(required[x] + used <= limit) { // 현재 과녁 점수를 얻을 수 있다면
            achieved[x] = true;
            backtracking(x-1, used + required[x], info);
        }
        achieved[x] = false;
        backtracking(x-1, used, info);
    }

    static int countScore(int[] info) {
        int apeachScore = 0;
        int rianScore = 0;
        for(int i=0; i<=10; i++) {
            if(achieved[i]) {
                rianScore += (10-i);
            } else if(info[i] > 0) {
                apeachScore += (10-i);
            }
        }
        return rianScore - apeachScore;
    }

    static void setAns(int remains) {
        ans = new int[11];
        for(int i=0; i<=10; i++) {
            if(achieved[i]) {
                ans[i] = required[i];
            }
        }
        ans[10] += remains;
    }
}