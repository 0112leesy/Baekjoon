import java.util.*;

class Solution {

    static class Edge {
        int destination;
        int fare;

        Edge(int destination, int fare) {
            this.destination = destination;
            this.fare = fare;
        }
    }

    static long[][] fareMatrix;

    public long solution(int n, int s, int a, int b, int[][] fares) {
        long[][] fareMatrix = floydWarshall(n, fares);
        long minFare = fareMatrix[s][a] + fareMatrix[s][b];
        for(int i=1; i<=n; i++) {
            if(i == s) continue;
            minFare = Math.min(minFare, fareMatrix[s][i] + fareMatrix[i][a] + fareMatrix[i][b]);
        }

        return minFare;
    }

    static long[][] floydWarshall(int n, int[][] fares) {
        fareMatrix = new long[n+1][n+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i == j) {
                    fareMatrix[i][j] = 0;
                    continue;
                }
                fareMatrix[i][j] = 2000000000;
            }
        }

        for(int i=0; i<fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int fare = fares[i][2];

            fareMatrix[from][to] = fare;
            fareMatrix[to][from] = fare;
        }

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    fareMatrix[i][j] = Math.min(fareMatrix[i][j], fareMatrix[i][k] + fareMatrix[k][j]);
                }
            }
        }

        return fareMatrix;

    }
}

/*

#1. 다익스트라: O(ElogV) * V => N*(N-1)/2 * logN * N
    - S에서, 모든 정점까지의 최단 경로 탐색
    - for(x in N) x에서 A,B 까지 최단 경로 djk(A), djk(B) 탐색 => djk(x) + djk(A) + djk(B) 로 값 갱신

#2. 걍 플로이드 와셜로 한 번에 모든 정점 사이의 최단거리 구하는걸로,,

*/