import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] amounts;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        amounts = new int[N][M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                amounts[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        // 초기화
        dp = new int[3][N][M];
        for(int i=0; i<M; i++) {
            dp[0][0][i] = amounts[0][i];
            dp[1][0][i] = amounts[0][i];
            dp[2][0][i] = amounts[0][i];
        }

        // 불가능한 경우
        for(int i=0; i<N; i++) {
            dp[0][i][0] = Integer.MAX_VALUE;
            dp[2][i][M-1] = Integer.MAX_VALUE;
        }

        runDp();

    }
    /*
    direction
        0  :   오른쪽
        1   :   중앙
        2   :   왼쪽
     */

    static void runDp() {
        for(int i=1; i<N; i++) {
            for(int j=0; j<M; j++) {
                // 왼쪽, 오른쪽 모두에서 올 수 있는 경우
                if(isValid(j-1) && isValid(j+1)) {
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + amounts[i][j];
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + amounts[i][j];
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + amounts[i][j];
                }
                // 오른쪽 끝에 있는 경우
                else if(!isValid(j-1) && isValid(j+1)) {
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + amounts[i][j];
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + amounts[i][j];
                }
                // 왼쪽 끝에 있는 경우
                else if(isValid(j-1) && !isValid(j+1)) {
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + amounts[i][j];
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + amounts[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for(int i=0; i<M; i++) {
            for(int j=0; j<3; j++) {
                min = Math.min(min, dp[j][N-1][i]);
            }
        }

        System.out.println(min);

    }

    static boolean isValid(int index) {
        if(index < 0 || index >= M) {
            return false;
        }
        return true;
    }

}