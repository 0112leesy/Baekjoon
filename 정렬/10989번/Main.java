import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { // 카운팅 정렬 알고리즘 사용 (O(N))
    // 수 끼리 비교하는 것이 아니기 때문에 정렬 속도가 매우 빠름
    // 그러나 별도의 배열을 생성해야 하며, 수의 범위가 클 때 메모리 사용량이 방대해져 비효율적임
    // 특수한 상황에 빠른 정렬을 할 수 있는 알고리즘
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 입력 배열
        int[] cnt = new int[10001]; // 카운팅 배열 (해당 인덱스의 수가 몇 번 입력되었는지 알 수 있음)
        int[] ans = new int[N]; // 정렬된 결과를 담을 배열
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            cnt[arr[i]]++; // 입력 받은 값을 입력 배열에 저장하고, 카운팅 배열에서 해당 값의 인덱스를 1 증가시킴
            
        }
        for(int i=1; i<=10000; i++){ // 카운팅 배열의 값들을 누적합으로 변경
            cnt[i] += cnt[i-1];
        }
        for(int i=N-1; i>=0; i--){ // 입력 배열의 마지막 값 -> 최초 값까지
        	cnt[arr[i]]--; // 카운팅 배열에서 해당 값의 인덱스에 담긴 값을 1 빼주고
            ans[cnt[arr[i]]] = arr[i]; // 결과 배열에서 1이 빼진 값의 인덱스에 입력 배열의 값을 위치시킴
        }
        // 반복문을 돌며 결과 배열 이곳 저곳에 입력 배열의 값들이 위치하게 되며, 최초 값까지 모두 위치시키게 되면 정렬이 완료됨
        StringBuilder sb = new StringBuilder();
        for(int i : ans){
        	if(i != 0) sb.append(i).append('\n');
        }
        System.out.println(sb);
    }
}
