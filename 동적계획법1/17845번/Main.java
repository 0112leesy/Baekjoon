import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static Subject[] subjects;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        subjects = new Subject[K+1];
        subjects[0] = new Subject(0, 0);
        for(int i=1; i<=K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int imp = Integer.parseInt(stringTokenizer.nextToken());
            int time = Integer.parseInt(stringTokenizer.nextToken());
            subjects[i] = new Subject(imp, time);
        }

        dp = new int[K+1][N+1];
        System.out.println(knapsack(K, N));

    }

    static class Subject {
        int imp;
        int time;

        Subject(int imp, int time) {
            this.imp = imp;
            this.time = time;
        }
    }

    static int knapsack(int k, int n) {
        if(k == 0) {
            return dp[k][n] = 0;
        }

        if(dp[k][n] != 0) {
            return dp[k][n];
        }

        if(n - subjects[k].time >= 0) {
            dp[k][n] = Math.max(knapsack(k-1, n),
                knapsack(k-1, n - subjects[k].time) + subjects[k].imp);
        }
        else {
            dp[k][n] = knapsack(k-1, n);
        }
        return dp[k][n];
    }

}