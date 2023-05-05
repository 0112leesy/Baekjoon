import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[][] profits = new int[N + 1][N + 1];
        int[][] sums = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= N; j++) {
                profits[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + profits[i][j];
            }
        }

        int max = Integer.MIN_VALUE;
        int K = -1;
        while (K++ < N) {
            for (int i = 1; i < N - K + 1; i++) {
                for (int j = 1; j < N - K + 1; j++) {
                    // (i, j) ~ (i+K, j+K)인 정사각형의 총 이익
                    int profit =
                        sums[i + K][j + K] - sums[i - 1][j + K] - sums[i + K][j - 1] + sums[i - 1][j
                            - 1];
                    ;
                    max = Math.max(max, profit);
                }

            }

        }

        System.out.println(max);


    }


}