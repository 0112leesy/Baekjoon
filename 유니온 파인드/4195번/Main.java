import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main { // 시간 초과, 수정 필요
	
	static HashMap<String, Integer> hmap;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			hmap = new HashMap<>();
			parents = new int[F*2];
			for(int i=0; i<parents.length; i++) parents[i] = i;
			
			for(int i=0; i<F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String F1 = st.nextToken();
				String F2 = st.nextToken();
				
				if(!hmap.containsKey(F1)) {
					hmap.put(F1, hmap.size());
				}
				if(!hmap.containsKey(F2)) {
					hmap.put(F2, hmap.size());
				}
				
				int F1_val = hmap.get(F1);
				int F2_val = hmap.get(F2);
				
				if(find(F1_val) != find(F2_val)) {
					union(F1_val, F2_val);
				}
				sb.append(get_size(find(F1_val))).append('\n');
			}
			
		}
		System.out.println(sb);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) parents[a] = b;
		else parents[b] = a;
	}
	
	static int find(int x) {
		if(parents[x] == x) return parents[x];
		return parents[x] = find(parents[x]);
	}
	
	static int get_size(int p) {
		int cnt = 0;
		for(int pars : parents) {
			if(find(pars) == p) cnt++;
		}
		return cnt;
	}

}