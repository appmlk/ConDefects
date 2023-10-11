import java.io.*;

// 処理
final class Process {
    private final int X;
    private final int Y;
    private final int Z;
    
    Process(final int X, final int Y, final int Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    // 結果を出力
    final void printResult(final PrintWriter printWriter) throws IOException {
        if(X > 0) {
            if(Y > X || Y < 0) {
                printWriter.println(X);
                return;
            }
            
            if(Z > Y)      printWriter.println(-1);
            else if(Z > 0) printWriter.println(X);
            else           printWriter.println(X + (-2) * Z);
            
            return;
        }
        
        if(Y < X || Y > 0) {
            printWriter.println(X);
            return;
        }
        
        if(Z < Y)      printWriter.println(-1);
        else if(Z < 0) printWriter.println(-X);
        else           printWriter.println(-X + 2 * Z);
    }
}

public class Main {
    public static void main(final String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter    printWriter    = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // 入力
        final String[] input = bufferedReader.readLine().trim().split("[ ]+");
        final int X = Integer.parseInt(input[0]);
        final int Y = Integer.parseInt(input[1]);
        final int Z = Integer.parseInt(input[2]);
        
        // Process クラスで処理を行う
        final Process process = new Process(X, Y, Z);
        process.printResult(printWriter);
        
        // 各ストリームを閉じる
        // 出力ストリームを閉じるときに標準出力に文字を出力する
        bufferedReader.close();
        printWriter.close();
    }
}