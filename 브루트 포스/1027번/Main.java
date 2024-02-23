import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        heights = new int[N];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i=0; i<N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for(int i=0; i<N; i++) {
            int cnt = 0;
            for(int j=0; j<N; j++) {
                if(i == j) continue;
                if(isWatchable(i, j)) {
                    cnt += 1;
                }
            }
            max = Integer.max(max, cnt);
        }

        System.out.println(max);
    }

    static boolean isWatchable(int n, int m) {
        boolean flag = true;
        double slope = ((double)(heights[n] - heights[m])) / (n-m);
        if(m < n) {
            for(int i=m+1; i<n; i++) {
                double check = ((double)(heights[n] - heights[i])) / (n-i);
                if(check <= slope) {
                    flag = false;
                    break;
                }
            }
        } else {
            for(int i=n+1; i<m; i++) {
                double check = ((double)(heights[n] - heights[i])) / (n-i);
                if(check >= slope) {
                    flag = false;
                    break;
                }
            }

        }
        return flag;
    }
}

/*

1. m < n
    - n이 더 높을 때, 기울기는 양수, 기울기가 작아지면 못봄
    - m이 더 높을 때, 기울기는 음수, 기울기가 작아지면 못봄
2. n < m
    - n이 더 높을 때, 기울기는 음수, 기울기가 커지면 못봄
    - m이 더 높을 때, 기울기는 양수, 기울기가 커지면 못봄

 */