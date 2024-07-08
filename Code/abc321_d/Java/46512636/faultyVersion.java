import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main( String[] args) {
        Scanner scn = new Scanner( System.in);
        int N = scn.nextInt();
        int M = scn.nextInt();
        long P = scn.nextLong();
        int[] maindishes = new int[N];
        int[] sidedishes = new int[M];
        for(int h = 0; h < N; h++) {
            maindishes[h] = scn.nextInt();
        }
        for(int i = 0; i < M; i++) {
            sidedishes[i] = scn.nextInt();
        }
        Arrays.sort( maindishes);
        Arrays.sort( sidedishes);
        long[] cumulativeMain = new long[N];
        long[] cumulativeSide = new long[M];
        cumulativeMain[0] = maindishes[0];
        cumulativeSide[0] = sidedishes[0];
        for(int j = 1; j < N; j++) {
            cumulativeMain[j] = cumulativeMain[j - 1] + maindishes[j];
        }
        for(int k = 1; k < M; k++) {
            cumulativeSide[k] = cumulativeSide[k - 1] + sidedishes[k];
        }
        long ans = 0;
        for(int mainPrice : maindishes) {
            long target = P - mainPrice;
            int rightIdx = 0;
            int leftIdx = M - 1;
            if(target <= sidedishes[0]) {
                ans += P * M;
                continue;
            }else if(sidedishes[M - 1] <= target) {
                ans += (long) mainPrice * cumulativeSide[M - 1];
                continue;
            }
            while(leftIdx - rightIdx > 1) {
                int mid = (rightIdx + leftIdx) / 2;
                if(sidedishes[mid] < target) {
                    rightIdx = mid;
                }else {
                    leftIdx = mid;
                }
            }
            long leTargetSum = (long)mainPrice * (rightIdx + 1) + cumulativeSide[rightIdx];
            long grTargetSum = P * (M - rightIdx - 1);
            ans += leTargetSum + grTargetSum;
        }
        System.out.println(ans);
    }
}