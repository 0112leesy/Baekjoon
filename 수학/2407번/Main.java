import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		if(m > n-m) {
			m = n-m;
		}
		
		BigInteger n1 = BigInteger.ONE;
		BigInteger n2 = BigInteger.ONE;
		// nCm = n * (n-1) * (n-2) * .. * (n-m+1) * m!
		for(int i=0; i<m; i++) {
			n1 = n1.multiply(new BigInteger(String.valueOf(n - i)));
			n2 = n2.multiply(new BigInteger(String.valueOf(i + 1)));
		}
		
		BigInteger answer = n1.divide(n2);
		System.out.println(answer);
		
		BigInteger bigNumber1 = new BigInteger("1000000");
		BigInteger bigNumber2 = new BigInteger("100000");
				
		//두 수 비교 compareTo 맞으면 0   틀리면 -1
		int compare = bigNumber1.compareTo(bigNumber2);
		System.out.println(compare);
		
	}
}