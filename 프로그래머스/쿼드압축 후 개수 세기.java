import java.util.*;

class Solution {

    static int zeroCount;
    static int oneCount;

    public int[] solution(int[][] arr) {
        zeroCount = 0;
        oneCount = 0;

        resizeQuad(0, 0, arr.length, arr);

        int[] answer = {zeroCount, oneCount};
        return answer;
    }

    static void resizeQuad(int x, int y, int n, int[][] arr) {
        if(n == 1) {
            if(arr[x][y] == 0) {
                zeroCount += 1;
            } else {
                oneCount += 1;
            }
            return;
        }

        int initNumber = arr[x][y];
        boolean isResizable = true;
        for(int i=x; i<x+n; i++) {
            for(int j=y; j<y+n; j++) {
                if(arr[i][j] != initNumber) {
                    isResizable = false;
                    break;
                }
            }
        }

        if(isResizable) {
            if(initNumber == 0) {
                zeroCount += 1;
            } else {
                oneCount += 1;
            }
            return;
        }

        resizeQuad(x, y, n/2, arr);
        resizeQuad(x, y+n/2, n/2, arr);
        resizeQuad(x+n/2, y, n/2, arr);
        resizeQuad(x+n/2, y+n/2, n/2, arr);
    }
}