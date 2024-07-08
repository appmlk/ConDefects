import java.util.*;
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        
        
        int count = 0;
        
        for(int i = 0; i < n; i++) {
          
          int a = sc.nextInt();
          
          if( a >= l) {
            count++;
          }
        }
        
        System.out.println(count);
    }
}