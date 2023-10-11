import java.util.*;

public class Main {
    public static void main(String[] args) {

        IOHandler io = new IOHandler();
        char[] s = io.nextStr().toCharArray();
        io.close();

        if (s[0] == '1') {
            io.output("No");
            return;
        }

        boolean[] isKeepStand = new boolean[7];
        isKeepStand[0] = s[6] == '1';
        isKeepStand[1] = s[3] == '1';
        isKeepStand[2] = s[1] == '1' || s[7] == '1';
        isKeepStand[3] = s[4] == '1';
        isKeepStand[4] = s[2] == '1' || s[8] == '1';
        isKeepStand[5] = s[5] == '1';
        isKeepStand[6] = s[9] == '1';

        // 異なる2列を選ぶ
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < i; j++) {

                // 2列に立っているピンが存在する場合
                if (isKeepStand[i] && isKeepStand[j]) {
                    // 2列の間で、ピンが立っている列
                    for (int k = j+1; k < i; k++) {
                        if (!isKeepStand[k]) {
                            io.output("Yes");
                            return;
                        }
                    }
                }
            }
        }
        io.output("No");
    }

    private static class IOHandler {
        private Scanner sc = new Scanner(System.in);
        private void close() {this.sc.close();}
        private String nextStr() {return this.sc.next();}
        private <T> void output(T result) {System.out.println(result);}
    }
}