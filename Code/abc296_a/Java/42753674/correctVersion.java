
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
//        while(t-->0){
            int n = sc.nextInt();
            String s = sc.next();
            boolean ok = false;
            for(int i=0;i<n-1;i++){
                if(s.charAt(i)==s.charAt(i+1)){
                    ok=true;
                    break;
                }
            }
            if(ok) System.out.println("No");
            else System.out.println("Yes");
        //}
    }
}
