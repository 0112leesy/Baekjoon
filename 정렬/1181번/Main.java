import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i<N; i++) {
        	arr.add(br.readLine());
        }
        Collections.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				if(s1.length() > s2.length()) return 1; // 문자열 길이 먼저 비교 후 
				else if(s1.length() == s2.length() && s1.compareTo(s2)>0) return 1; // 길이가 같다면 사전순으로 비교
				else return -1; // return s1.length() - s2.length() 처럼 구현할 수도 있음
			}
        	
        });
        StringBuilder sb = new StringBuilder();
        sb.append(arr.get(0)).append('\n');
        for(int i=1; i<N; i++) { // 중복 제거 (이전 문자열과 다를 때만 추가함)
        	if(!arr.get(i).equals(arr.get(i-1))) sb.append(arr.get(i)).append('\n');
        }
        System.out.println(sb);
	}

}