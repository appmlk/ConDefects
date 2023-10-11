import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String a = sc.next();
        String b = sc.next();
        
        String[] aarr = a.split("");
        String[] barr = b.split("");
        
        int n = aarr.length < barr.length ? aarr.length : barr.length;
        
        String result = "Easy";
        for(int i  = 0;  i < n; i++){
          int aint = Integer.parseInt(aarr[aarr.length - 1 - i]);
          int bint = Integer.parseInt(barr[barr.length - 1 - i]);
          if(aint + bint >= 10){
            result = "Hard";
          }
        }
        
        System.out.println(result);
    }
}