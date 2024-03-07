import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int TRAIN_COUNT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine()); // max: 50,000
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // max: 100
        }
        int limit = Integer.parseInt(bufferedReader.readLine());

        int[] cumulativeSumUntil = new int[N+1];
        for(int i=1; i<=N; i++) {
            cumulativeSumUntil[i] = cumulativeSumUntil[i-1] + arr[i-1];
        }

        int[][] dp = new int[TRAIN_COUNT+1][N+1];
        for(int i=1; i<=TRAIN_COUNT; i++) {
            for(int j=i*limit; j<=N; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-limit] + (cumulativeSumUntil[j]-cumulativeSumUntil[j-limit]));
            }
        }

        System.out.println(dp[TRAIN_COUNT][N]);
    }
}

/*

for(int i=1; i<=3; i++) { i: 소형차 수
    for(int j=i*limit; j<=N; j++) { 현재꺼 선택 했을때랑 선택 안했을 때 중 큰 값 저장
        dp[i][j] = Math.max(dp[i][j-1], dp[i][j-limit] + sum[j] - sum[j-limit])

 */