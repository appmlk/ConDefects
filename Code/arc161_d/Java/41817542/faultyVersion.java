import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = sc.nextInt(),d = sc.nextInt();
        if(n*d>n*(n-1)/2){
            out.println("No");

        }else {
            out.println("Yes");
            int sum = n*d;
            int end = 1;
            Set<String> set = new HashSet<>();
            int cnt =0;
            out:while (true){
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <=d; j++) {
                        out.println(i + " " + ((j+i-1)%n+1));
                        sum--;
                        if(sum==0){
                            break out;
                        }
                    }
//                    int a = i,  b = ((i+end)%(n)==0?n:(i+end)%(n));
//                    int max = Math.max(a,b);
//                    int min = Math.min(a,b);
//                    if(set.contains(min + " " + max)){
//                        continue ;
//                    }
//
//                    System.out.println(cnt++);
//                    set.add(min + " " + max);

                }
                end++;
            }

        }



        out.close();

    }

}