import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		for(int i=0; i<N; i++) parent[i] = i;
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int status = Integer.parseInt(st.nextToken());
				if(status == 1) union(i, j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int init = Integer.parseInt(st.nextToken()) - 1;
		boolean check = true;
		for(int i=1; i<M; i++) {
			int city = Integer.parseInt(st.nextToken()) - 1;
			if(find(init) != find(city)) {
				check = false;
				break;
			}
		}
		System.out.println(check? "YES" : "NO");
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			if(a > b) parent[a] = b;
			else parent[b] = a;
		}
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}
