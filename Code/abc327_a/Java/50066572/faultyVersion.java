import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        String ln = br.readLine();
        pw.println(ln.contains("ab") || ln.contains("ba") ? "YES" : "NO");
        pw.close();
    }
}