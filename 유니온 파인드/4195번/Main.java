import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static HashMap<String, Integer> hmap;
	static int[] parents;
	static int[] size; // 집합의 크기를 저장하는 배열, 각 집합은 parent로 구분됨

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			hmap = new HashMap<>();
			parents = new int[F*2];
			size = new int[F*2];
			for(int i=0; i<parents.length; i++) {
				parents[i] = i;
				size[i] = 1;
			}
			
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
				sb.append(size[find(F1_val)]).append('\n');
			}
		}
		System.out.println(sb);
	}
	// 집합의 크기를 합치는 기능 추가
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) {
			parents[a] = b;
			size[b] += size[a];
			size[a] = 0;
		}
		else {
			parents[b] = a;
			size[a] += size[b];
			size[b] = 0;
		}
	}
	
	static int find(int x) {
		if(parents[x] == x) return parents[x];
		return parents[x] = find(parents[x]);
	}

}