import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static ArrayList<Edge> edges;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());

        edges = new ArrayList<>();
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, distance));
        }

        Collections.sort(edges);

        parent = new int[N+1];
        for(int i=0; i<=N; i++) {
            parent[i] = i;
        }

        int totalDistance = 0;
        for(Edge e : edges) {
            if(find(e.from) != find(e.to)) {
                union(e.from, e.to);
                totalDistance += e.distance;
            }
        }

        System.out.println(totalDistance);

    }

    static class Edge implements Comparable<Edge>{
        private int from;
        private int to;
        private int distance;

        Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge e) {
            return this.distance - e.distance;
        }

    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a <= b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

}