import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        a = a.substring(0,5);
    
        String b = sc.nextLine();
        b = b.substring(0,5);
        boolean c = false;
        boolean d = false;
        if(a.equals("R G B")||a.equals("G B R")||a.equals("B R G")){
             c = true;
        }
        if(b.equals("R G B")||b.equals("G B R")||b.equals("B R G")){
            d = true;
            
        }
        if(c&&d){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }
        sc.close();
    }
}
