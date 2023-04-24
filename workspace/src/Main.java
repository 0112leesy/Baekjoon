import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import org.w3c.dom.Node;

public class Main {

    static int N;
    static int M;
    static ArrayList<ArrayList<Edge>> graph;



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new Edge(1, 0));

        for(int i=0; i<M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int nodeA = Integer.parseInt(stringTokenizer.nextToken());
            int nodeB = Integer.parseInt(stringTokenizer.nextToken());
            int dist = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(nodeA).add(new Edge(nodeB, dist));
            graph.get(nodeB).add(new Edge(nodeA, dist));
        }

        System.out.println(dijkstra());
    }

    static class Edge {
        private int node;
        private int dist;

        Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int dijkstra() {
        Queue<Edge> queue = new LinkedList<>();
        int[] minDistance = new int[N+1];
        for(int i=2; i<=N; i++) {
            minDistance[i] = Integer.MAX_VALUE;
        }

        queue.add(graph.get(0).get(0));

        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            int node = edge.node;

            for (Edge next : graph.get(node)) {
                if (minDistance[next.node] > minDistance[node] + next.dist) {
                    minDistance[next.node] = minDistance[node] + next.dist;
                    queue.offer(next);
                }
            }
        }

        return minDistance[N];
    }
}