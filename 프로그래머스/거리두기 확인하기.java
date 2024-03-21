import java.util.*;

class Solution {

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public int[] solution(String[][] places) {

        int[] answer = new int[places.length];
        for(int i=0; i<places.length; i++) {
            char[][] place = convert(places[i]);
            ArrayList<Integer[]> pSpot = new ArrayList<>();
            boolean flag = true;

            for(int x=0; x<5; x++) {
                for(int y=0; y<5; y++) {
                    if(place[x][y] == 'P') {
                        pSpot.add(new Integer[] {x, y});
                    }
                }
            }

            for(int j=0; j<pSpot.size(); j++) {
                for(int k=j+1; k<pSpot.size(); k++) {
                    int distance = getManhatDistance(pSpot.get(j)[0], pSpot.get(j)[1],
                            pSpot.get(k)[0], pSpot.get(k)[1]);
                    if(distance == 1) {
                        flag = false;
                        break;
                    } else if(distance == 2) {
                        flag = isOK(pSpot.get(j)[0], pSpot.get(j)[1],
                                pSpot.get(k)[0], pSpot.get(k)[1], place);
                        if(!flag) break;
                    }
                }
                if(!flag) break;
            }

            answer[i] = flag?1:0;

        }

        return answer;
    }

    static char[][] convert(String[] placeInfo) {
        char[][] place = new char[5][5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                place[i][j] = placeInfo[i].charAt(j);
            }
        }
        return place;
    }

    static int getManhatDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static boolean isOK(int x1, int y1, int x2, int y2, char[][] place) { // 맨해튼 거리가 2인 좌석들 검사
        if(x1 == x2) {
            if(place[x1][(y1+y2)/2] == 'X') {
                return true;
            }
            return false;
        }
        if(y1 == y2) {
            if(place[(x1+x2)/2][y1] == 'X') {
                return true;
            }
            return false;
        }

        if(place[x1][y2] == 'X' && place[x2][y1] == 'X') {
            return true;
        }
        return false;
    }

}