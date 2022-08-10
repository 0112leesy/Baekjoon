import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] in_arr = new int[N+1];
		int[][] mat = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			for(int j=0; j<num-1; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(mat[start][x] == 0) {
					mat[start][x] = 1;
					in_arr[x]++;
				}
				start = x;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(in_arr[i] == 0) queue.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append('\n');
			for(int i=1; i<=N; i++) {
				if(mat[now][i] == 1) {
					in_arr[i]--;
					if(in_arr[i] == 0) queue.offer(i);
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(in_arr[i] != 0) {
				sb = new StringBuilder();
				sb.append(0);
			}
		}
		
		System.out.println(sb);
	}

}
