import java.util.*;

public class Main {
    public static void Solution(String str,int a,int b){
        for(int i=a;i<=b;i++){
            System.out.print(str.charAt(i-1));
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str="atcoder";
        int a=sc.nextInt();
        int b=sc.nextInt();

        Solution(str,a,b);
        
        sc.close();
    } 
}
