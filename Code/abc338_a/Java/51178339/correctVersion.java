
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        boolean ok = false;
        if(s.charAt(0)>='A' && s.charAt(0)<='Z') {
            for(int i=1;i<s.length();i++){
                if(s.charAt(i)>='A' && s.charAt(i)<='Z'){
                    ok=true;
                    break;
                }
            }
        }
        else {
            ok=true;
        }
        System.out.println(ok?"No":"Yes");
    }
}
