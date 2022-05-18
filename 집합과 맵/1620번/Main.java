import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<Integer, String> num_map = new HashMap<>();
		Map<String, Integer> name_map = new HashMap<>();
		int num = 1;
		while(num <= N) {
			String name = br.readLine();
			num_map.put(num, name);
			name_map.put(name, num);
			num++;
		}
		
		StringBuilder sb = new StringBuilder();
		while(M-- > 0) {
			String input = br.readLine();
			if(48 < input.charAt(0) && input.charAt(0) < 58) {
				int input_num = Integer.parseInt(input);
				sb.append(num_map.get(input_num)).append('\n');
			}
			else {
				sb.append(name_map.get(input)).append('\n');
			}
		}
		System.out.println(sb);
	}

}
