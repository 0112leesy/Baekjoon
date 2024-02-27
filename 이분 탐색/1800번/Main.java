import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, P, K;
    static ArrayList<ArrayList<Integer[]>> graph;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<P; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(node1).add(new Integer[]{node2, cost});
            graph.get(node2).add(new Integer[]{node1, cost});
        }

        binarySearch(0, 1000000);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }

    static void binarySearch(int l, int r) {
        while(l <= r) {
            int mid = (l + r) / 2;
            if(dijkstra(mid)) {
                ans = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
    }

    static boolean dijkstra(int limit) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1] - o2[1];
            }
        });

        distance[1] = 0;
        pq.offer(new Integer[]{1, 0});

        while(!pq.isEmpty()) {
            Integer[] node = pq.poll();

            int now = node[0];
            int cost = node[1];

            if(distance[now] < cost) {
                continue;
            }

            for(Integer[] n : graph.get(now)) {
                int next = n[0];
                int nextCost = cost;

                if(n[1] > limit) { // limit 보다 긴 케이블
                    nextCost += 1;
                }

                if(nextCost < distance[next]) {
                    distance[next] = nextCost;
                    pq.offer(new Integer[]{next, nextCost});
                }
            }

        }
        return distance[N] <= K;
    }

}