
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String t = sc.next();
        String s[] = new String[n];

        int maxLen = 500001;
        int preCount[] = new int[maxLen];
        int sufCount[] = new int[maxLen];
        for(int i = 0; i < n; i++) {
            s[i] = sc.next();

            int tmpIndex = 0;
            for(int j = 0; j < s[i].length(); j++) {
                if(s[i].charAt(j) == t.charAt(tmpIndex)) {
                    tmpIndex++;
                    if(tmpIndex == t.length()) {
                        break;
                    }
                }
            }
            preCount[tmpIndex]++;

            tmpIndex = t.length() - 1;
            for(int j = s[i].length() - 1; j >= 0; j--) {
                if(s[i].charAt(j) == t.charAt(tmpIndex)) {
//                    System.out.println("Hit : j = " + j + " in " + s[i]);
                    tmpIndex--;
                    if(tmpIndex == -1) {
                        break;
                    }
                }
            }
            sufCount[t.length() - tmpIndex - 1]++;
        }

        int sufSum[] = new int[maxLen];
        sufSum[0] = sufCount[0];
        for(int i = 1; i < maxLen; i++) {
            sufSum[i] = sufSum[i - 1] + sufCount[i];
        }

//        for (int i = 0; i < 10; i++) {
//            System.out.println("i = " + i + " preCount " + preCount[i] + " sufCount " + sufCount[i]);
//
//        }

        long result = 0;
        for(int i = 0; i < maxLen; i++) {
            if(preCount[i] == 0) {
                continue;
            }

//            System.out.println("i = " + i);

            int tmp = t.length() - i - 1;
            if(tmp >= 0) {
                result += (long) (n - sufSum[tmp]) * preCount[i];
            }
            else {
                result += (long)n * preCount[i];
            }
        }

        System.out.println(result);
    }
}
