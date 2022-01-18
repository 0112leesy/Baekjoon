import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] road = new long[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<(N-1); i++) road[i] = Integer.parseInt(st.nextToken());
		long[] cost = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) cost[i] = Integer.parseInt(st.nextToken());
		
		long ans = road[0] * cost[0];
		long current_cost = cost[0];
		for(int i=1; i<(N-1); i++) {
			if(current_cost > cost[i]) {
				ans += road[i] * cost[i];
				current_cost = cost[i];
			}
			else {
				ans += road[i] * current_cost;
			}
		}
		System.out.println(ans);
	}
}