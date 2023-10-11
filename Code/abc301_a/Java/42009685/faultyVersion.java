import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        String s = scanner.next();
        
        int count = 0;
        for(int i=0; i<n; i++){
            if(s.charAt(i) == 'T'){
                count++;
            }
        }
        
        
        
        int nn = n-1;
        if(count == 0){
            System.out.println("A");
        }else if(count == n-count){
            for(int i=nn; i>0; i--){
                if(s.charAt(i) != s.charAt(i-1)){
                    System.out.println(s.charAt(i-1));
                    break;
                }
            }
        }else if(count > n-count){
            System.out.println("T");
        }
        
    }
}
