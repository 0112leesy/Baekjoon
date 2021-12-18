import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] cnt = new int[10001];
        int[] ans = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            cnt[arr[i]]++;
            
        }
        for(int i=1; i<=10000; i++){
            cnt[i] += cnt[i-1];
        }
        for(int i=N-1; i>=0; i--){
        	cnt[arr[i]]--;
            ans[cnt[arr[i]]] = arr[i];
        }
        StringBuilder sb = new StringBuilder();
        for(int i : ans){
        	if(i != 0) sb.append(i).append('\n');
        }
        System.out.println(sb);
    }
}
