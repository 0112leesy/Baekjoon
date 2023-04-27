import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int s, N, K, R_1, R_2, C_1, C_2;
    static int[][] answer;
    static ArrayList<Integer> mid;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        s = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        R_1 = Integer.parseInt(stringTokenizer.nextToken());
        R_2 = Integer.parseInt(stringTokenizer.nextToken());
        C_1 = Integer.parseInt(stringTokenizer.nextToken());
        C_2 = Integer.parseInt(stringTokenizer.nextToken());

        answer = new int[R_2 - R_1 + 1][C_2 - C_1 + 1];
        mid = getMid(N % 2);

        fractal(0, 0, s, false);
        for(int i=0; i<answer.length; i++) {
            for(int j=0; j< answer[0].length; j++) {
                System.out.print(answer[i][j]);
            }
            System.out.println();
        }

    }

    static ArrayList<Integer> getMid(int remain) { // 중앙 index를 구하는 함수
        ArrayList<Integer> midIndex = new ArrayList<>();
        if(remain == 0) { // 짝수일 때;
            for(int i=0; i<K/2; i++) {
                midIndex.add(N/2-1 - i);
                midIndex.add(N/2 + i);
            }
            return midIndex;
        }
        // 홀수일 때
        midIndex.add(N/2);
        for(int i=1; i<=K/2; i++) {
            midIndex.add(N/2 - i);
            midIndex.add(N/2 + i);
        }
        return midIndex;
    }

    static boolean continueFractal(int x, int y, int s) { // 탐색할 범위가 정답을 구할 범위 내에 있는지 확인
        int size = (int)Math.pow(N, s);
        int dx = x + size - 1;
        int dy = y + size - 1;

        // 탐색할 상자와 정답을 구할 상자가 겹치는지 확인하는 로직
        if(x > R_2 || dx < R_1 || y > C_2 || dy < C_1) return false;
        return true;
    }

    static void fractal(int x, int y, int s, boolean isBlack) {
        if(s == 0) { // 상자 크기가 1일 때
            if(R_1 <= x && x <= R_2 && C_1 <= y && y <= C_2) { // 정답을 구할 범위 내 라면 저장
                if(isBlack) {
                    answer[x - R_1][y - C_1] = 1;
                    return;
                }
                answer[x - R_1][y - C_1] = 0;
                return;
            }
            return;
        }

        if(!continueFractal(x, y, s)) { // 탐색 여부 확인
            return;
        }

        int gap = (int)Math.pow(N, (s-1));
        if(isBlack) { // 모든 하위 상자들의 isBlack를 true로 줌
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    fractal(x + i * gap, y + j * gap, s-1, true);
                }
            }
            return;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(mid.contains(i) && mid.contains(j)) { // 중앙 상자만 isBlack를 true로 줌
                    fractal(x + i * gap, y + j * gap, s-1, true);
                }
                else {
                    fractal(x + i * gap, y + j * gap, s-1, false);
                }
            }
        }
    }

}