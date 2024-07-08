import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N =sc.nextInt();
        String S =sc.next();
        boolean end = true;
        for(int i = 0;i < N-2;i++){
            
        if((S.substring(i,i+3)).matches("ABC")){
            System.out.println(i+1);
            end = false;
            break;
        }
        
        
        }
        if(end){System.out.println(-1);}
    
}
}
