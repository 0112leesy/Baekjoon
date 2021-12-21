import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		ArrayList<Integer> sorted_arr = (ArrayList<Integer>)arr.clone(); // 정렬할 리스트 복사
		Collections.sort(sorted_arr);
		int idx = 0;
		HashMap<Integer, Integer> hmap = new HashMap<>(); // key: 원본 좌표, value: 압축된 좌표 (정렬 결과를 바탕으로 좌표 값이 주어짐)
		for(Integer num : sorted_arr) {
			if(!hmap.containsKey(num)) { // key의 중복 주의
				hmap.put(num, idx++);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(Integer num : arr) { // 원본 리스트에 담긴 좌표 순으로 해당 좌표의 압축된 좌표를 해시맵에서 찾아서 결과 도출
			sb.append(hmap.get(num)+" ");
		}
		System.out.println(sb);
		
	}

}