import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[] leaks;
    static int cnt;
    static double x;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        leaks = new int[N];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            leaks[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(leaks);
        System.out.println(getCnt());
    }

    static int getCnt() {
        cnt = 0;
        x = 0;

        for (int i = 0; i < leaks.length; i++) {
            if (x < leaks[i] - 0.5) { // 못 미침
                x = leaks[i] - 0.5 + L;
                cnt++;
            } else if (x >= leaks[i] + 0.5) { // 전부 덮음
                continue;
            } else {
                x += L;
                cnt++;
            }
        }

        return cnt;
    }

}