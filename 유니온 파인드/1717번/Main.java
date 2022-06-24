import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		for(int i=0; i<=n; i++) {
			parent[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(op == 0) union(a,b);
			else {
				if(find(a) == find(b)) sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
		
	}
	
	static void union(int a, int b) {
        // a의 부모와 b의 부모가 다르다면
        // 더 큰 값을 가진 부모를 값이 작은 부모로 재지정해줘야 함
		a = find(a);
		b = find(b);
		if(a != b) {
			if(a > b) parent[a] = b;
			else parent[b] = a;
		}
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
        // 재귀 연산에서의 최적화를 위해 값을 재지정해준 뒤 리턴
		return parent[x] = find(parent[x]);
	}


}
