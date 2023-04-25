import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int[] scores;
    static int[] sumScores;
    static int totalScores;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        Q = Integer.parseInt(stringTokenizer.nextToken());
        scores = new int[N];
        sumScores = new int[N];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for(int i=0; i<N; i++) {
            scores[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        totalScores = 0;
        for(int i=0; i<N; i++) {
            int sum = scores[i % N] * scores[(i+1) % N] * scores[(i+2) % N] * scores[(i+3) % N];
            sumScores[i] = sum;
            totalScores += sum;
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<Q; i++) {
            int index = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            stringBuilder.append(changeSumScores(index)).append(" ");
        }

        System.out.println(stringBuilder.toString());
    }

    static int changeSumScores(int index) {
        int origin = sumScores[validateIndex(index-3)] + sumScores[validateIndex(index-2)] +
            sumScores[validateIndex(index-1)] + sumScores[validateIndex(index)];

        updateSumScores(index);
        int changed = sumScores[validateIndex(index-3)] + sumScores[validateIndex(index-2)] +
            sumScores[validateIndex(index-1)] + sumScores[validateIndex(index)];

        totalScores -= (origin - changed);
        return totalScores;
    }

    static void updateSumScores(int index) {
        scores[index] = scores[index] * -1;
        for(int i=0; i<4; i++) {
            sumScores[validateIndex(index - i)] = sumScores[validateIndex(index - i)] * (-1);
        }
    }

    static int validateIndex(int index) {
        return (N + index) % N;
    }
}