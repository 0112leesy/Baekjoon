import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, S, X, Y;
    static int[][] map;
    static PriorityQueue<Virus> pq;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static class Virus {
        int type;
        int x;
        int y;
        int time;

        Virus(int type, int x, int y, int time) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public void spread() {
            for(int i=0; i<4; i++) {
                int newx = x + dx[i];
                int newy = y + dy[i];

                if(newx < 0 || newx >= N || newy < 0 || newy >= N) continue;
                if(map[newx][newy] == 0) {
                    map[newx][newy] = type;
                    pq.offer(new Virus(type, newx, newy, time+1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][K];
        pq = new PriorityQueue<>(new Comparator<Virus>() {
            @Override
            public int compare(Virus o1, Virus o2) {
                if(o1.time == o2.time) {
                    return o1.type - o2.type;
                }
                return o1.time - o2.time;
            }
        });

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) {
                    pq.offer(new Virus(map[i][j], i, j, 0));
                }
            }
        }

        st = new StringTokenizer(bufferedReader.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for(int i=0; i<S; i++) {
            int size = pq.size();
            for(int j=0; j<size; j++) {
                Virus virus = pq.poll();
                virus.spread();
            }
        }

        System.out.println(map[X-1][Y-1]);
    }
}