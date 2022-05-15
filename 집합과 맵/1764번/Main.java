import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new TreeMap<>();
		for(int i=0; i<N; i++) {
			String name = br.readLine();
			map.put(name, 1);
		}
		for(int i=0; i<M; i++) {
			String name = br.readLine();
			if(map.getOrDefault(name, 0) == 1) map.replace(name, 2);
		}
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(String name : map.keySet()) {
			if(map.get(name) == 2) {
				sb.append(name).append('\n');
				cnt++;
			}
		}
		System.out.println(cnt);
		System.out.println(sb);
	}
	
}
