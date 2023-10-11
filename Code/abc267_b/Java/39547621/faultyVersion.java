import java.util.*;

public class Main {

    public static boolean check(char[] s) {
        if(s[0] == 1) return false;
        else {
            int[] cols = new int[7];
            cols[0] = s[6] - '0';
            cols[1] = s[3] - '0';
            cols[2] = s[1] - '0' + s[7] - '0';
            cols[3] = s[0] - '0' + s[4] - '0';
            cols[4] = s[2] - '0' + s[8] - '0';
            cols[5] = s[5] - '0';
            cols[6] = s[9] - '0';
            for(int i = 0; i < 6; i++) {
                for(int j = i+1; j < 7; j++) {
                    if(j-i >= 2 && cols[i] + cols[j] >= 2) {
                        int cnt = 0;
                        for(int k = i; k <= j; k++) {
                            cnt += cols[k];
                        }
                        if(cnt == cols[i] + cols[j]) return true;
                    }
                }
            }
            return false;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        System.out.println(check(s) ? "Yes" : "No");
    }
}