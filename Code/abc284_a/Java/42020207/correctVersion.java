import java.util.*;

public class Main {
    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);  
      int n = sc.nextInt();
      String[] s = new String[n];
      for(int i = 0;i<n;i++){
        s[i] = sc.next();
      }

      for(int i = n-1;i >= 0 ;i--){
       System.out.println(s[i]);
      }

    }
}
