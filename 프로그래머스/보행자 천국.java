class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {

        int[][][] ways = new int[m][n][2]; // 0: 오른쪽 방향, 1: 아래쪽 방향
        int[][] sum = new int[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i == 0 && j == 0) {
                    ways[i][j][0] = 1;
                    sum[i][j] = 1;
                    continue;
                }

                if(cityMap[i][j] == 1) {
                    sum[i][j] = 0;
                    continue;
                }

                if(i > 0) {
                    ways[i][j][1] += ways[i-1][j][1] % MOD;
                    if(cityMap[i-1][j] == 0) {
                        ways[i][j][1] += ways[i-1][j][0] % MOD;
                    }
                }

                if(j > 0) {
                    ways[i][j][0] += ways[i][j-1][0] % MOD;
                    if(cityMap[i][j-1] == 0) {
                        ways[i][j][0] += ways[i][j-1][1] % MOD;
                    }
                }

                sum[i][j] = (ways[i][j][0]%MOD + ways[i][j][1]%MOD) % MOD;
            }
        }

        return sum[m-1][n-1];
    }
}