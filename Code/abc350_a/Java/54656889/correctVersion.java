import java.util.*;

public class Main{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String S = scan.nextLine();
        String letters = S.replaceAll("[0-9]", "");
        String numbers = S.replaceAll("[^0-9]", "");
        int intNum = Integer.parseInt(numbers);
        
        if((intNum == 316) || (intNum >= 350) || (intNum == 0)){
          System.out.println("No");
        }else{
          System.out.println("Yes");
        }
        
        scan.close();
    }
}