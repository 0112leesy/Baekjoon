import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    // 정렬 후 각 통계값을 구하거나,
    // 카운팅 정렬을 하면서 통계값을 구할 수 있음 -> 범위가 정해졌으며, 그 값이 크지 않기 때문에 더 빠른 결과 처리가 가능함
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<N; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr); // 입력받은 값들이 정렬됨
        
        int avg = arr.get(0);
        int mid = arr.get(N/2);
        int mode = 0;
        ArrayList<Integer> idx_arr = new ArrayList<>(); // 정렬된 배열에서 값이 바뀔 때마다 인덱스 저장할 리스트
        idx_arr.add(0); // 시작 인덱스
        int range = arr.get(N-1) - arr.get(0);
        for(int i=1; i<N; i++) {
        	int num = arr.get(i);
        	avg += num;
        	if(num != arr.get(i-1)) { // 값이 바뀔 때마다 해당 인덱스 저장
        		idx_arr.add(i);
        	}
        }
        idx_arr.add(N); // 마지막 인덱스
        avg = (int)Math.round(avg * 1.0 / N);

        int max_cnt = 0;
        int ans_idx = 0;
        boolean flag = true;
        for(int i=1; i<idx_arr.size(); i++) {
        	int cnt = idx_arr.get(i) - idx_arr.get(i-1); // 동일한 수가 얼마나 나타나는지 범위 구하기
        	if(cnt > max_cnt) { // max_cnt 범위와 비교
        		max_cnt = cnt;
        		ans_idx = idx_arr.get(i-1);
        		flag = true; // max_cnt 범위가 갱신되면 flag는 true로 변경. 두번째로 작은 최빈값을 구하기 위함
        	}
        	else if(cnt == max_cnt && flag) { // max_cnt 범위와 같고, flag가 true면 두번째로 작은 최빈값으로써 인덱스 저장
        		max_cnt = cnt;
        		ans_idx = idx_arr.get(i-1);
        		flag = false; // 더이상 같은 범위의 최빈값은 무시
        	}
        }
        mode = arr.get(ans_idx); // 정렬된 arr 리스트에서 ans_idx 인덱스의 값이 구하려는 최빈값이 됨
        
        System.out.println(avg+"\n"+mid+"\n"+mode+"\n"+range);
    }
}
