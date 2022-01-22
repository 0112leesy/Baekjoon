import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(br.readLine());
		int[] arr = new int[total];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<total; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		if(total % 2 == 1) System.out.println(arr[total/2]*arr[total/2]);
		else System.out.println(arr[0]*arr[total-1]);
	}

}