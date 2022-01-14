import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer[]> arr = new ArrayList<>();
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			Integer[] temp = new Integer[2];
			temp[0] = Integer.parseInt(st.nextToken());
			temp[1] = Integer.parseInt(st.nextToken());
			arr.add(temp);
		}
		
		Collections.sort(arr, new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				if(o1[1] == o2[1]) return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
			
		});
		
		int cnt = 1;
		int end = arr.get(0)[1];
		for(int i=1; i<N; i++) {
			if(end <= arr.get(i)[0]) {
				end = arr.get(i)[1];
				cnt ++;
			}
		}
		System.out.println(cnt);
	}
	
}