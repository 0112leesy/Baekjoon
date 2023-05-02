import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] arr;
    static ArrayList<Integer[]> calculations;
    static boolean[] selected;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        calculations = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());

            calculations.add(new Integer[]{r, c, s});
        }

        min = Integer.MAX_VALUE;
        selected = new boolean[K + 1];

        permutation(0, new ArrayList<>());
        System.out.println(min);

    }

    // x1, y1, x2, y2 외곽을 회전하여 arr 값을 갱신하는 함수
    static int[][] rotate(int[][] arr, int x1, int y1, int x2, int y2) {
        int temp = arr[x1][y2];
        for (int i = y2; i > y1; i--) {
            arr[x1][i] = arr[x1][i - 1];
        }

        int temp2 = arr[x2][y2];
        for (int i = x2; i > x1; i--) {
            arr[i][y2] = arr[i - 1][y2];
        }

        arr[x1 + 1][y2] = temp;
        int temp3 = arr[x2][y1];

        for (int i = y1; i < y2; i++) {
            arr[x2][i] = arr[x2][i + 1];
        }

        arr[x2][y2 - 1] = temp2;

        for (int i = x1; i < x2; i++) {
            arr[i][y1] = arr[i + 1][y1];
        }
        arr[x2 - 1][y1] = temp3;

        return arr;
    }

    // r, c, s가 주어졌을 때 배열을 회전하는 함수
    static void calculate(int[][] arr, int r, int c, int s) {
        for (int i = 1; i <= s; i++) {
            rotate(arr, r - i, c - i, r + i, c + i);
        }
    }

    // 배열A 값을 구하는 함수
    static int getValue(int[][] arr) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += arr[i][j];
            }
            minValue = Math.min(minValue, sum);
        }
        return minValue;
    }

    // 연산 순서(순열) 별로 배열을 회전시키고 배열A 값을 구한 뒤 최솟값을 갱신한다.
    static void permutation(int depth, ArrayList<Integer[]> list) {
        if (depth == K) {
            int[][] testArr = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    testArr[i][j] = arr[i][j];
                }
            }

            for (Integer[] calculation : list) {

                calculate(testArr, calculation[0], calculation[1], calculation[2]);

            }
            int value = getValue(testArr);
            min = Math.min(min, value);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!selected[i]) {
                selected[i] = true;
                list.add(calculations.get(i));
                permutation(depth + 1, list);
                list.remove(list.get(list.size() - 1));
                selected[i] = false;
            }
        }
    }


}