import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {

        int maxAlp = 0;
        int maxCop = 0;
        for(int i=0; i<problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }

        int[][] dp = new int[200][200];
        for(int i=0; i<200; i++) {
            Arrays.fill(dp[i], 1000000);
        }
        for(int i=0; i<=alp; i++) {
            for(int j=0; j<=cop; j++) {
                dp[i][j] = 0;
            }
        }

        for(int a=0; a<=maxAlp; a++) {
            for(int c=0; c<=maxCop; c++) {
                dp[a+1][c] = Math.min(dp[a+1][c], dp[a][c] + 1);
                dp[a][c+1] = Math.min(dp[a][c+1], dp[a][c] + 1);

                int idx = problems.length-1;
                for(int i=0; i<=idx; i++) {
                    if(canSolve(a, c, problems[i])) {
                        int nextA = a + problems[i][2];
                        int nextC = c + problems[i][3];

                        nextA = Math.min(nextA, maxAlp); // maxAlp, maxCo으로 상한선 설정
                        nextC = Math.min(nextC, maxCop);

                        dp[nextA][nextC] = Math.min(dp[nextA][nextC], dp[a][c] + problems[i][4]);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }

    static boolean canSolve(int alp, int cop, int[] problem) {
        if(alp >= problem[0] && cop >= problem[1]) {
            return true;
        }
        return false;
    }
}

/*
alp: 10
cop: 10

[[10,15,2,1,2],
[20,20,3,3,4]]

max_alp = 20;
max_cop = 20;

1. 알고력을 높인다.
2. 코딩력을 높인다.
3. 풀 수 있는 문제 q를 푼다.

dp[alp][cop] : alp, cop를 달성하기 위한 최단시간

Q[alp_req, cop_req, alp_rwd, cop_rwd, cost]
if(alp >= alp_req && cop >= cop_req) {
    dp[alp+alp_rwd][cop+cop_rwd] = dp[alp][cop] + cost;
}

dp[alp+1][cop] = dp[alp][cop] + 1;
dp[alp][cop+1] = dp[alp][cop] + 1;
*/