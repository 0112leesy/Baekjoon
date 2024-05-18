import java.util.*;

class Solution {
    static ArrayList<Integer[]>[] graph;
    static int INF = 1000000;


    public int solution(int N, int[][] road, int K) {
        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            graph[i] = new ArrayList<Integer[]>();
        }

        for(int i=0; i<road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];

            graph[a].add(new Integer[]{b, c});
            graph[b].add(new Integer[]{a, c});
        }

        int[] distance = dijkstra(N);
        int cnt = 0;
        for(int i=1; i<=N; i++) {
            if(distance[i] <= K) {
                cnt += 1;
            }
        }

        return cnt;
    }

    static int[] dijkstra(int N) {
        int[] distance = new int[N+1];
        Arrays.fill(distance, INF);
        distance[0] = 0;
        distance[1] = 0;

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new Integer[]{1, 0});

        while(!pq.isEmpty()) {
            Integer[] now = pq.poll();

            for(Integer[] next : graph[now[0]]) {
                if(distance[next[0]] > distance[now[0]] + next[1]) {
                    distance[next[0]] = distance[now[0]] + next[1];
                    pq.offer(next);
                }
            }
        }

        return distance;
    }
}