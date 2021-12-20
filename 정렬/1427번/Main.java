import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
        for(int i=0; i<arr.length; i++){
            arr[i] -= '0';
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(int i : arr) sb.append(i);
        System.out.println(sb.reverse());
	}

}