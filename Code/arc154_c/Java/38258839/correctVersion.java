import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        var sc = new Scanner(System.in);
        var pw = new PrintWriter(System.out);
        
        int T = Integer.parseInt(sc.next());
        label:for(int t = 0; t < T; t++){
            int n = Integer.parseInt(sc.next());
            var a = new int[n];
            for(int i = 0; i < n; i++){
                a[i] = Integer.parseInt(sc.next());
            }
            var b = new int[n];
            for(int i = 0; i < n; i++){
                b[i] = Integer.parseInt(sc.next());
            }
            
            var a2 = new ArrayList<Integer>();
            a2.add(a[0]);
            for(int i = 1; i < n; i++){
                if(a[i] != a[i-1]){
                    a2.add(a[i]);
                }
            }
            if(a2.size() >= 2 && a[0] == a[n-1]){
                a2.remove(a2.size()-1);
            }
            
            var b2 = new ArrayList<Integer>();
            b2.add(b[0]);
            for(int i = 1; i < n; i++){
                if(b[i] != b[i-1]){
                    b2.add(b[i]);
                }
            }
            if(b2.size() >= 2 && b[0] == b[n-1]){
                b2.remove(b2.size()-1);
            }
            
            int sizeA = a2.size();
            int sizeB = b2.size();
            if(sizeA < sizeB){
                pw.println("No");
                continue;
            }
    
            for(int i = 0; i < sizeB; i++){
                int indexB = i;
                int count = 0;
                for(Integer ai : a2){
                    if(ai.equals(b2.get(indexB))){
                        indexB = (indexB + 1) % sizeB;
                        count++;
                        if(count == sizeB && (sizeB < n || (sizeB == n && i == 0))){
                            pw.println("Yes");
                            continue label;
                        }
                    }
                }
            }
            pw.println("No");
        }
        pw.flush();
    }
}