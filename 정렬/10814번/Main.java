import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class member{
	int age;
	String name;
	
	member(int mem_age, String mem_name){
		age = mem_age;
		name = mem_name;
	}
}

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<member> arr = new ArrayList<>();
		StringTokenizer st;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int mem_age = Integer.parseInt(st.nextToken());
        	String mem_name = st.nextToken();
        	arr.add(new member(mem_age, mem_name));
        }
        Collections.sort(arr, new Comparator<member>() {

			@Override
			// age만 비교하면 age가 같을 때는 자동으로 입력 순으로 정렬됨 (안정정렬)
			public int compare(member m1, member m2) {
				return m1.age - m2.age;
			}
        	
        });
        StringBuilder sb = new StringBuilder();
        for(member m : arr) {
        	sb.append(m.age+" "+m.name).append('\n');
        }
        System.out.println(sb);
	}

}