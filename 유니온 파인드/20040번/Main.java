import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for(int i=0; i<n; i++) parent[i] = i;
		
		int end = -1;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			if(find(num1) != find(num2)) {
				union(num1, num2);
			}
			else {
				end = i+1;
				break;
			}
		}
		System.out.println(end == -1? 0 : end);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) parent[a] = b;
		else parent[b] = a;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}
