import java.util.*;


public class Main {
    public static void main(String[] args) {
        
    
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    String T = sc.next();
    int d = 0;
    int x = 0;
    int y = 0;
    
    
    char[] t = T.toCharArray();
    
    
    
    for(int i = 0; i < t.length; i++){
        if(t[i] == 'S'){
            if(d==0) x++;
            if(d==1) y--;
            if(d==2) x--;
            if(d==3) y++;


        } else{
            d = (d+1)%4;
        }
        
        
    }
    System.out.println(x);
    System.out.println(y);


    }

}
