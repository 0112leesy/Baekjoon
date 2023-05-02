import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        arr = new int[N];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(arr);

        answer = new StringBuilder();
        dfs(0, new ArrayList<Integer>());

        System.out.println(answer.toString());
    }

    static void dfs(int depth, ArrayList<Integer> list) {
        if (depth == M) {
            StringBuilder seq = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                seq.append(list.get(i)).append(" ");
            }
            answer.append(seq).append("\n");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
            dfs(depth + 1, list);
            list.remove(list.size() - 1); // 백트래킹
        }
    }
}