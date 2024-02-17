import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] mem = new int[N+1];
        st = new StringTokenizer(bufferedReader.readLine());
        for(int i=1; i<=N; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }

        int[] cost = new int[N+1];
        st = new StringTokenizer(bufferedReader.readLine());
        for(int i=1; i<=N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][10001]; // dp[i][j]: i번째 앱까지 고려했을 때, cost j에서의 최대 메모리 크기
        for(int c=0; c<=10000; c++) {
            for(int i=1; i<=N; i++) {
                dp[i][c] = dp[i-1][c];
                if(cost[i] <= c) {
                    dp[i][c] = Math.max(dp[i-1][c], dp[i-1][c-cost[i]] + mem[i]);
                }
            }
        }

        for(int c=0; c<=10000; c++) {
            if(dp[N][c] >= M) {
                System.out.println(c);
                break;
            }
        }
    }
}