import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] bizAmount;
    static long[][][][][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        bizAmount = new int[5];
        // dp[pprev][prev][a][b][c][d][e] : 전전 비즈, 전 비즈 그리고 각 비즈(최대 5개)의 수(최대 10개)가
        //                                  다음과 같이 주어졌을 때 목걸이를 만들 수 있는 경우의 수
        dp = new long[6][6][11][11][11][11][11];

        // -1로 초기화
        for(int i=0; i<6; i++) {
            for(int j=0; j<6; j++) {
                for(int k=0; k<11; k++) {
                    for(int l=0; l<11; l++) {
                        for(int m=0; m<11; m++) {
                            for(int n=0; n<11; n++) {
                                for(int o=0; o<11; o++) {
                                    dp[i][j][k][l][m][n][o] = -1;
                                }
                            }
                        }
                    }
                }
            }
        }

        int N = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < N; i++) { // 각 비즈의 수 입력
            bizAmount[i] = Integer.parseInt(bufferedReader.readLine());
        }

        System.out.println(
            getDp(0, 0, bizAmount[0], bizAmount[1], bizAmount[2], bizAmount[3], bizAmount[4]));


    }

    static long getDp(int pprev, int prev, int a, int b, int c, int d, int e) {
        if ((pprev != 0 && prev != 0) && pprev == prev) { // 전전 비즈와 전 비즈가 목걸이에 끼워져 있을 때 두 비즈가 같으면 경우의 수는 0
            return dp[pprev][prev][a][b][c][d][e] = 0;
        }
        if (a < 0 || b < 0 && c < 0 || d < 0 || e < 0) { // ?? 검토 필요
            return dp[pprev][prev][a][b][c][d][e] = 0;
        }
        if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0) { // 비즈를 모두 다 쓰면(목걸이가 완성되면) 경우의 수는 1
            return dp[pprev][prev][a][b][c][d][e] = 1;
        }

        long ret = dp[pprev][prev][a][b][c][d][e];

        if (ret != -1) { // 메모이제이션
            return ret;
        }

        // 전전 비즈와 겹치지 않도록 비즈를 끼웠을 때 모든 경우의 수를 더하여 저장
        ret = 0;
        if (a > 0 && pprev != 1) {
            ret += getDp(prev, 1, a - 1, b, c, d, e);
        }
        if (b > 0 && pprev != 2) {
            ret += getDp(prev, 2, a, b - 1, c, d, e);
        }
        if (c > 0 && pprev != 3) {
            ret += getDp(prev, 3, a, b, c - 1, d, e);
        }
        if (d > 0 && pprev != 4) {
            ret += getDp(prev, 4, a, b, c, d - 1, e);
        }
        if (e > 0 && pprev != 5) {
            ret += getDp(prev, 5, a, b, c, d, e - 1);
        }

        return dp[pprev][prev][a][b][c][d][e] = ret;
    }
}