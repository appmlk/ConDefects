import java.util.*;
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int a = sc.nextInt();
        
        int count = 0;
        
        for(int i = 0; i < n; i++) {
          if( a >= l) {
            count++;
          }
        }
        
        System.out.println(count);
    }
}