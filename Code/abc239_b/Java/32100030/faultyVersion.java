import java.util.*;


public class Main {
    public static void main(String[] args) {
        
    
    Scanner sc = new Scanner(System.in);
    
    long X = sc.nextLong();
    long rsm = X / 10;
    if(X % 10 != 0 && rsm < 0){
        rsm -= 1;
        System.out.println(rsm);
    } else{
        System.out.println(rsm);
    }

    

    
        
        


    }


}