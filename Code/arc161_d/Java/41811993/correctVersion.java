import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    
        var sc = new Scanner(System.in);
        
        int n = Integer.parseInt(sc.next());
        int d = Integer.parseInt(sc.next());
        
        if(d*n > (long)n*(n-1)/2){
            System.out.println("No");
            return;
        }
        
        var pw = new PrintWriter(System.out);
        pw.println("Yes");
        for(int i = 1; i <= d; i++){
            for(int a = 0; a < n; a++){
                int b = (a+i)%n;
                pw.println((a+1) + " " + (b+1));
            }
        }
        pw.flush();
    }
}