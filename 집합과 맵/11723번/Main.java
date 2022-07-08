import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int[] ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Set<Integer> set = new HashSet<>();
		
		StringTokenizer st;
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if(cmd.equals("all")) {
				set.clear();
				for(int i=1; i<=20; i++) set.add(i);
			}
			else if(cmd.equals("empty")) {
				set.clear();
			}
			else {
				int num = Integer.parseInt(st.nextToken());
				if(cmd.equals("add")) {
					set.add(num);
				}
				else if(cmd.equals("remove")) {
					if(set.contains(num)) set.remove(num);
				}
				else if(cmd.equals("check")) {
					if(set.contains(num)) sb.append(1).append('\n');
					else sb.append(0).append('\n');
				}
				else if(cmd.equals("toggle")) {
					if(set.contains(num)) set.remove(num);
					else set.add(num);
				}
			}
			
		}
		System.out.println(sb);
	}
}
