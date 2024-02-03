import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int candies[];
    static boolean visited[];
    static ArrayList<ArrayList<Integer>> graph;
    static int cnt;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 사탕 정보 저장
        candies = new int[N+1];
        st = new StringTokenizer(bufferedReader.readLine());
        for(int i=1; i<=N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        // 친구 정보 저장
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());

        // dfs를 통해 연결된 친구들 수와 사탕의 합계를 구하여 list에 담기
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                cnt = 1;
                int sum = dfs(i);
                ArrayList<Integer> linked = new ArrayList();
                linked.add(sum);
                linked.add(cnt);
                list.add(linked);
            }
        }

        // 사탕의 합 => value, 친구 수 => weight로 가정하여 냅색 알고리즘 활용
        int size = list.size();
        int bag[][] = new int[K][size];
        for(int i=1; i<K; i++) { // 각 무게에 대해서
            for(int j=1; j<size; j++) { // 차례대로 물건을 담는다
                int V = list.get(j).get(0);
                int W = list.get(j).get(1);
                if(W <= i) { // 현재 물건을 담을 수 있다면
                    bag[i][j] = Math.max(bag[i][j-1], bag[i-W][j-1] + V);
                } else {
                    bag[i][j] = bag[i][j-1];
                }
            }
        }

        System.out.println(bag[K-1][size-1]);
    }

    static int dfs(int x) {
        int sum = candies[x];
        for(int friend : graph.get(x)) {
            if(!visited[friend]) {
                visited[friend] = true;
                cnt += 1;
                sum += dfs(friend);
            }
        }
        return sum;
    }

}