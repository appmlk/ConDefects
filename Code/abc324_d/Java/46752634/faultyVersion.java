
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String numStr = sc.next();
        int count[] = new int[10];
        for(int i = 0; i < numStr.length(); i++) {
            int digit = numStr.charAt(i) - '0';
            count[digit]++;
        }

        int tmpCount[] = new int[10];
        long result = 0;
loop:        for(long i = 1; i <= 4000000; i++){
            Arrays.fill(tmpCount, 0);
            long sq = i*i;

            if(Math.log10(sq) > numStr.length()) {
                break;
            }

            long sqCopy = sq;

            while(sqCopy > 0) {
                tmpCount[(int)(sqCopy%10)]++;
                sqCopy /= 10;
            }

            for(int j = 1 ; j <= 9; j++) {
                if(count[j] != tmpCount[j]) {
                    continue loop;
                }
            }

//            System.out.println("hit " + sq);
            result++;
        }

//        long factorial[] = new long[14];
//        factorial[0] = 1;
//        for (int i = 1; i <= 13; i++) {
//            factorial[i] = factorial[i - 1] * i;
//        }
//
//        for(int i = 0; i <= 9; i++) {
//            result *= factorial[count[i]];
//        }

        System.out.println(result);

    }
}
