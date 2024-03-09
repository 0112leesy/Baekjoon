import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] honeys;
    static int[] cumulativeHoneys;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        honeys = new int[N];
        cumulativeHoneys = new int[N];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i=0; i<N; i++) {
            honeys[i] = Integer.parseInt(st.nextToken());
        }

        cumulativeHoneys[0] = honeys[0];
        for(int i=1; i<N; i++) {
            cumulativeHoneys[i] = cumulativeHoneys[i-1] + honeys[i];
        }

        int maxHoney = cumulativeHoneys[N-1];
        /**
         * case1: 벌통은 맨 오른쪽 끝에 고정, 첫 번째 벌을 왼쪽 끝에 고정
         */
        int case1Max = -1;
        for(int i=1; i<N-1; i++) {
            int betaHoney = cumulativeHoneys[N-1] - cumulativeHoneys[i];
            int alphaHoney = maxHoney - honeys[0] -honeys[i];
            case1Max = Math.max(case1Max, alphaHoney + betaHoney);
        }

        /**
         * case2: 벌통은 맨 왼쪽 끝에 고정, 첫 번째 벌을 오른쪽 끝에 고정
         */
        int case2Max = -1;
        for(int i=N-2; i>0; i--) {
            int betaHoney = cumulativeHoneys[i-1];
            int alphaHoney = maxHoney - honeys[N-1] - honeys[i];
            case2Max = Math.max(case2Max, alphaHoney + betaHoney);
        }

        /**
         * case3: 첫 번째 벌과 두 번째 벌 양쪽 끝에 고정
         */
        int case3Max = -1;
        for(int i=1; i<N-1; i++) {
            int betaHoney = maxHoney - cumulativeHoneys[i-1] - honeys[N-1];
            int alphaHoney = cumulativeHoneys[i] - honeys[0];
            case3Max = Math.max(case3Max, alphaHoney + betaHoney);
        }

        System.out.println(Math.max(case1Max, Math.max(case2Max, case3Max)));
    }
}