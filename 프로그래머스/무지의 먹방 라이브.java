import java.util.*;

class Solution {

    static long kCount;

    public int solution(int[] food_times, long k) {
        long total_times = 0;
        for(int i=0; i<food_times.length; i++) {
            total_times += food_times[i];
        }
        if(total_times <= k) {
            return -1;
        }

        long looped = binarySearch(food_times, k);
        int ans = 0;
        for(int i=0; i<food_times.length; i++) {
            if(food_times[i] > looped) {
                kCount += 1;
            }
            if(kCount > k) {
                ans = i;
                break;
            }
        }
        return ans+1;
    }

    static long binarySearch(int[] food_times, long k) {
        long l = 1L;
        long r = k;
        long mid = 0;
        long ans = 0;

        while(l <= r) {
            mid = (l + r) / 2;
            if(isLessThanK(mid, food_times, k)) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    static boolean isLessThanK(long looped, int[] food_times, long k) {
        long cnt = food_times.length * looped;
        for(int i=0; i<food_times.length; i++) {
            if(food_times[i] - looped < 0) {
                cnt += (food_times[i] - looped);
            }
        }
        if(cnt <= k) {
            kCount = cnt;
            return true;
        }
        return false;
    }
}

/*

[10,    21,     4,      33,     6]
[6,     17,     0,      29,     2] : 5*4 = 20
[4,     15,     0,      27,     0] : 20+2*4 = 28
[3,     14,     0,      26,     0] : 28+1*3 = 31

## 이분탐색
1 ~ 100,000,000(..k) -> mid : 7 //

check(mid) { mid바퀴 돌았을 때의 K값
    [3,    14,     -3,      26,     -1] => mid*5 - 3 - 1 =  31
}

*/