import java.util.*;

class Solution {

    static int[][][] distance;
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int INF = 400000;
    static int STREIGHT = 100;
    static int CORNER = 600;

    public int solution(int[][] board) {

        N = board.length;
        distance = new int[N][N][4];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(distance[i][j], INF);
            }
        }

        dijkstra(board);

        return getMinDistance(N-1, N-1);
    }

    static void dijkstra(int[][] board) {
        // Integer[x][y][d] -> d: 현재 방향 (0 : ->, 1 : V, 2 : <- , 3 : ^)
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(
                (o1, o2) -> getMinDistance(o1[0], o1[1]) - getMinDistance(o2[0], o2[1]));

        if(board[0][1] == 0) {
            pq.offer(new Integer[]{0, 1});
            distance[0][1][0] = STREIGHT;
        }

        if(board[1][0] == 0) {
            pq.offer(new Integer[]{1, 0});
            distance[1][0][1] = STREIGHT;
        }

        while(!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int x = now[0];
            int y = now[1];

            for(int i=0; i<4; i++) {
                if(distance[x][y][i] == INF) continue;

                for(int j=0; j<4; j++) {
                    int newx = x + dx[j];
                    int newy = y + dy[j];

                    if(newx < 0 || newx >= N || newy < 0 || newy >= N || board[newx][newy] == 1) {
                        continue;
                    }

                    int cost = isCorner(i, j)?CORNER:STREIGHT;
                    if(distance[newx][newy][j] > distance[x][y][i] + cost) {
                        distance[newx][newy][j] = distance[x][y][i] + cost;
                        pq.offer(new Integer[]{newx, newy});
                    }
                }

            }
        }

    }

    static int getMinDistance(int x, int y) {
        int min = INF+1;
        for(int i=0; i<4; i++) {
            min = Math.min(min, distance[x][y][i]);
        }
        return min;
    }

    static boolean isCorner(int d, int nextd) {
        switch(d) {
            case 0:
                if(nextd == 1 || nextd == 3) return true;
                break;

            case 1:
                if(nextd == 0 || nextd == 2) return true;
                break;

            case 2:
                if(nextd == 1 || nextd == 3) return true;
                break;

            case 3:
                if(nextd == 0 || nextd == 2) return true;
                break;
        }
        return false;
    }
}