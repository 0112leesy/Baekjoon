import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int W[][];
    static int dp[][];
    static final int CANNOT_CYCLE = 17 * 1000000 + 1;
    static final int NOT_VISIT = CANNOT_CYCLE * 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        W = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j=0; j<N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[city][visited] : 현재 city에 위치해있고, visited만큼 도시를 방문했을 때, 남은 도시를 모두 방문하고 출발지로 돌아왔을 때의 최소 거리
        // (1 << N) -1 : 모든 도시를 방문했을 때의 비트마스크
        dp = new int[N][(1 << N) - 1];
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], NOT_VISIT);
        }

        System.out.println(getDp(0, 1));
    }

    static int getDp(int city, int visited) {
        if(visited == (1 << N) - 1) { // 모든 도시를 방문한 경우
            if(W[city][0] == 0) { // 현재 도시에서 출발지로 가는 길이 없다면
                return CANNOT_CYCLE; // NOT_VISIT 보다 작은 CANNOT_CYCLE 리턴
            }
            return W[city][0]; // 그렇지 않다면 현재 도시에서 출발지까지의 거리 리턴
        }
        if(dp[city][visited] != NOT_VISIT) { // dp 배열에 값이 저장되어 있다면 그대로 리턴
            return dp[city][visited];
        }
        for(int i=0; i<N; i++) { // 모든 도시에 대해서
            if((W[city][i] != 0) && ((visited & (1 << i)) == 0)) { // 현재 도시로부터 길이 있고, 아직 방문하지 않은 도시라면
                dp[city][visited] = Math.min(dp[city][visited], getDp(i, visited | (1 << i)) + W[city][i]);
                // getDp(다음 도시, 다음 도시를 방문했을 때의 비트마스크) + 현재 도시에서 다음 도시까지의 거리
                // 를 구해서 현재 값보다 작다면 갱신
            }
        }
        return dp[city][visited];
    }

}

/*
CANNOT_CYCLE 과 NOT_VISITED 를 구분하지 않고,
모든 도시를 방문했을 때 출발지로 돌아가는 길이 없는 경우 NOT_VISITED 를 반환했었다.
따라서 나중에 방문하지 않은 상황으로 판단하여 다시 방문하는 상황이 발생해 시간초과가 났었다.
 */