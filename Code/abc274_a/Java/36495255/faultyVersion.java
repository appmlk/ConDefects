import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int A = sc.nextInt();
        int B = sc.nextInt();
        if(A == B){
            System.out.println("1.000");
        }else{
            sb.append("0.");
            B = B*10000;
            double c = B/A;
            c /= 10;
            long res = Math.round(c);
            sb.append(Long.toString(res));
            String ans = sb.toString();
            System.out.println(ans);
        }
        sc.close();
    }
}