import java.util.*;

class Solution {

    static int[][] table;

    public boolean solution(int[][] key, int[][] lock) {
        int M = key[0].length;
        int N = lock[0].length;

        table = new int[3*N][3*N];
        for(int i=0; i<3*N; i++) {
            Arrays.fill(table[i], 1);
        }
        for(int i=N; i<2*N; i++) {
            for(int j=N; j<2*N; j++) {
                table[i][j] = lock[i-N][j-N];
            }
        }

        int iter = 4;
        boolean ans = false;
        while(iter-- > 0) {

            for(int i=0; i+M<3*N; i++) {
                for(int j=0; j+M<3*N; j++) {
                    int[][] tmpTable = new int[3*N][3*N];

                    for(int tx=0; tx<M; tx++) {
                        for(int ty=0; ty<M; ty++) {
                            tmpTable[tx+i][ty+j] = key[tx][ty];
                        }
                    }

                    boolean flag = true;
                    for(int x=N; x<2*N; x++) {
                        for(int y=N; y<2*N; y++) {
                            if(tmpTable[x][y] + table[x][y] != 1) {
                                flag = false;
                                break;
                            }
                        }
                        if(!flag) {
                            break;
                        }
                    }
                    if(flag) {
                        ans = true;
                        break;
                    }
                }
                if(ans) {
                    break;
                }
            }
            key = rotate(key, M);
        }
        return ans;
    }

    static int[][] rotate(int[][] key, int M) {
        int[][] rotated = new int[M][M];
        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) {
                rotated[j][M-1 - i] = key[i][j];
            }
        }
        return rotated;
    }
}