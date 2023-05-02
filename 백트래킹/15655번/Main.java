import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static boolean[] selected;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        arr = new int[N+1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(arr);

        selected = new boolean[N+1];
        answer = new StringBuilder();
        dfs(0, 0);

        System.out.println(answer.toString());
    }

    static void dfs(int depth, int index) {
        if (depth == M) {
            StringBuilder seq = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                if (selected[i]) {
                    seq.append(arr[i]).append(" ");
                }
            }
            answer.append(seq).append("\n");
            return;
        }

        for (int i = index + 1; i <= N; i++) {
            selected[i] = true;
            dfs(depth + 1, i);
            selected[i] = false; // 백트래킹
        }
    }
}