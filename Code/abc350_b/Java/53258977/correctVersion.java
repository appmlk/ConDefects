import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 歯の本数
        int Q = sc.nextInt(); // 治療の回数
        
        // 歯の状態を管理する配列を初期化 (true: 歯が生えている, false: 歯が生えていない)
        boolean[] teeth = new boolean[N + 1]; // インデックス1から使うため、サイズをN+1に設定
        
        // 治療を行う
        for (int i = 0; i < Q; i++) {
            int hole = sc.nextInt(); // 穴の番号
            
            // 治療内容に応じて穴の状態を更新する
            if (teeth[hole]) {
                teeth[hole] = false; // 歯が生えている場合は歯を抜く
            } else {
                teeth[hole] = true; // 歯が生えていない場合は歯を生やす
            }
        }
        
        // 歯の本数を数える
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (teeth[i]) { // 歯が生えている場合は本数をカウントする
                count++;
            }
        }
        
        // 答えを出力する
        System.out.println(N-count);
    }
}