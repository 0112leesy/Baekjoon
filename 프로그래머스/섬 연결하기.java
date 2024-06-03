import java.util.*;

class Solution {

    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }

    public int solution(int n, int[][] costs) {

        parent = new int[n+1];
        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }

        ArrayList<Edge> edges = new ArrayList<>();
        for(int i=0; i<costs.length; i++) {
            edges.add(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }

        Collections.sort(edges);

        int answer = 0;
        for(Edge e : edges) {
            if(find(e.from) != find(e.to)) {
                union(e.from, e.to);
                answer += e.dist;
            }
        }

        return answer;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a <= b) {
            parent[b] = a;
            return;
        }
        parent[a] = b;
    }

    static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return find(parent[x]);
    }
}