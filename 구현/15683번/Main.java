import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] office;
    static ArrayList<Integer[]> walls;
    static ArrayList<Cam> cams;
    static int minCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];
        walls = new ArrayList<>();
        cams = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for(int j=0; j<M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if(office[i][j] != 0 && office[i][j] != 6) {
                    cams.add(new Cam(i, j, office[i][j]));
                } else if(office[i][j] == 6) {
                    walls.add(new Integer[]{i, j});
                }
            }
        }

        minCnt = Integer.MAX_VALUE;
        combination(0);

        System.out.println(minCnt);
    }

    static void combination(int index) {
        if(index == cams.size()) {
            // 사각지대 구하기
            int[][] tmpOffice = new int[N][M];
            for (Integer[] wall : walls) {
                tmpOffice[wall[0]][wall[1]] = 6;
            }
            for (Cam cam : cams) {
                tmpOffice[cam.x][cam.y] = cam.type;
            }

            for (Cam cam : cams) {
                cam.watch(tmpOffice);
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tmpOffice[i][j] == 0) {
                        cnt += 1;
                    }
                }
            }

            minCnt = Math.min(minCnt, cnt);
            return;
        }

        Cam now = cams.get(index);
        for(int i=0; i<now.selected.length; i++) {
            if(!now.selected[i]) {
                now.selected[i] = true;
                now.mode = i;
                combination(index+1);
                now.selected[i] = false;
            }
        }
    }

    static class Cam {

        int x;
        int y;
        int type;
        int mode;
        boolean[] selected;

        Cam(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
            if(type == 1 || type == 3 || type == 4) {
                selected = new boolean[4];
            } else if(type == 2) {
                selected = new boolean[2];
            } else {
                selected = new boolean[1];
            }
        }

        void watch(int[][] tmpOffice) {
            if(type == 1) {
                singleWatch(mode+1, tmpOffice);
            } else if(type == 2) {
                if(mode == 0) {
                    singleWatch(1, tmpOffice);
                    singleWatch(3, tmpOffice);
                } else {
                    singleWatch(2, tmpOffice);
                    singleWatch(4, tmpOffice);
                }
            } else if(type == 3) {
                if(mode == 0) {
                    singleWatch(1, tmpOffice);
                    singleWatch(4, tmpOffice);
                } else if(mode == 1) {
                    singleWatch(1, tmpOffice);
                    singleWatch(2, tmpOffice);
                } else if(mode == 2) {
                    singleWatch(2, tmpOffice);
                    singleWatch(3, tmpOffice);
                } else {
                    singleWatch(3, tmpOffice);
                    singleWatch(4, tmpOffice);
                }

            } else if(type == 4) {
                if(mode == 0) {
                    singleWatch(1, tmpOffice);
                    singleWatch(3, tmpOffice);
                    singleWatch(4, tmpOffice);
                } else if(mode == 1) {
                    singleWatch(1, tmpOffice);
                    singleWatch(2, tmpOffice);
                    singleWatch(4, tmpOffice);
                } else if(mode == 2) {
                    singleWatch(1, tmpOffice);
                    singleWatch(2, tmpOffice);
                    singleWatch(3, tmpOffice);
                } else {
                    singleWatch(2, tmpOffice);
                    singleWatch(3, tmpOffice);
                    singleWatch(4, tmpOffice);
                }
            } else {
                for(int i=1; i<=4; i++) {
                    singleWatch(i, tmpOffice);
                }
            }
        }

        private void singleWatch(int direction, int[][] tmpOffice) {
            switch (direction) {
                case 1:
                    for(int i=y+1; i<M; i++) {
                        if(tmpOffice[x][i] != 6) {
                            if(tmpOffice[x][i] == 0) {
                                tmpOffice[x][i] = -1;
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                case 2:
                    for(int i=x+1; i<N; i++) {
                        if(tmpOffice[i][y] != 6) {
                            if(tmpOffice[i][y] == 0) {
                                tmpOffice[i][y] = -1;
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                case 3:
                    for(int i=y-1; i>=0; i--) {
                        if(tmpOffice[x][i] != 6) {
                            if(tmpOffice[x][i] == 0) {
                                tmpOffice[x][i] = -1;
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                case 4:
                    for(int i=x-1; i>=0; i--) {
                        if(tmpOffice[i][y] != 6) {
                            if(tmpOffice[i][y] == 0) {
                                tmpOffice[i][y] = -1;
                            }
                        } else {
                            break;
                        }
                    }
                    break;
            }
        }
    }
}