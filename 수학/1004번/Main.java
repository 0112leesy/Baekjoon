import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Planet {
        int cx;
        int cy;
        int r;

        Planet(int cx, int cy, int r) {
            this.cx = cx;
            this.cy = cy;
            this.r = r;
        }

        double contains(int x, int y) {
            return r - Math.sqrt(Math.pow(x-cx, 2) + Math.pow(y-cy, 2));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int cnt = 0;
            int n = Integer.parseInt(bufferedReader.readLine());
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(bufferedReader.readLine());
                Planet planet = new Planet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));

                if(planet.contains(x1, y1) * planet.contains(x2, y2) < 0) {
                    cnt += 1;
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}