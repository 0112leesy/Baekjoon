import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
 
 
public class Main {	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(br.readLine());
			HashMap<String, Integer> hmap = new HashMap<>();
			StringTokenizer st;
			for(int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String k = st.nextToken();
				if(hmap.containsKey(k)) {
					hmap.put(k, hmap.get(k)+1);
				}
				else {
					hmap.put(k, 1);
				}
			}
			int result = 1;
			for(Integer v:hmap.values()) {
				int temp = v + 1;
				result *= temp;
			}
			sb.append(result-1).append("\n");
		}
		System.out.println(sb);
	}
	
 
}