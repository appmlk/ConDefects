import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        n = sc.nextInt();
        
        sc.nextLine();
        
        int counter = 0;
        for(int i = 0;i < n;i++){
          String s = sc.nextLine();
          if(s.equals("Takashi"))counter++;
        }
        
        System.out.println(counter);
    }
}