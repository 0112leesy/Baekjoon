import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int weightNumber;
	static int[] weights;
	static int marbleNumber;
	static int[] marbles;
	static boolean isPossible[][];
	static final int MAX_WEIGHT_SUM = 15000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		weightNumber = Integer.parseInt(br.readLine());
		weights = new int[weightNumber];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<weightNumber; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		marbleNumber = Integer.parseInt(br.readLine());
		marbles = new int[marbleNumber];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<marbleNumber; i++) {
			marbles[i] = Integer.parseInt(st.nextToken());
		}
	
		isPossible = new boolean[weightNumber+1][MAX_WEIGHT_SUM+1];
		dfs(0,0);
		System.out.println(getAnswer());
		
	}	
	
	static void dfs(int weightCount, int currentWeight) {
		// 이미 방문된 경우 돌아감
		if(isPossible[weightCount][currentWeight]) {
			return;
		}
		
		// 방문된 경우는 true 저장 (방문되다 == 도달하다 == 측정할 수 있다)
		isPossible[weightCount][currentWeight] = true;
		
		// 추의 개수를 초과하게 되면 돌아감
		if(weightCount == weightNumber) {
			return;
		}
		
		// 1. 구슬이 없는 쪽에 추를 놓는 경우
		dfs(weightCount+1, currentWeight+weights[weightCount]);
		// 2. 아무쪽에도 놓지 않는 경우
		dfs(weightCount+1, currentWeight);
		// 3. 구슬이 있는 쪽에 추를 놓는 경우
		dfs(weightCount+1, Math.abs(currentWeight - weights[weightCount]));
	}
	
	static String getAnswer() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<marbleNumber; i++) {
			int marbleWeight = marbles[i];
			if(marbleWeight > MAX_WEIGHT_SUM) {
				sb.append("N ");
				continue;
			}
			if(isPossible[weightNumber][marbleWeight]) {
				sb.append("Y ");
			}
			else {
				sb.append("N ");
			}
		}
		return sb.toString();
	}
	
}