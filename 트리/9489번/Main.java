import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n == 0 && k == 0) {
                break;
            }

            int[] nodes = new int[n+1];
            int[] parents = new int[n+1];
            int idx = -1;
            parents[0] = idx;
            nodes[0] = -1;
            int targetNode = 0;
            st = new StringTokenizer(bufferedReader.readLine());
            for(int i=1; i<=n; i++) {
                nodes[i] = Integer.parseInt(st.nextToken());
                if(nodes[i] == k) {
                    targetNode = i;
                }
                if(nodes[i] != nodes[i-1]+1) {
                    idx += 1;
                }
                parents[i] = idx;
            }

            int cnt = 0;
            for(int i=1; i<=n; i++) {
                if(parents[i] != parents[targetNode] && parents[parents[i]] == parents[parents[targetNode]]) {
                    cnt += 1;
                }
            }
            stringBuilder.append(cnt).append('\n');

        }
        System.out.println(stringBuilder);
    }

}