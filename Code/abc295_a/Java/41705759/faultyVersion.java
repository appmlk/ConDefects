
import java.util.Scanner;

/**
 * @author: Mostafa Murad
 * @created: 5/22/2023 on 09:27 PM
 **/
public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int idx=-1;
        for(int i=0;i<n;i++)
        {
            String s=in.next();
            if(s.contains("and") || s.contains("not") || s.contains("that") || s.contains("the") || s.contains("you"))
            {
                idx=0;
                break;
            }
        }
        if(idx==0) System.out.println("Yes");
        else System.out.println("No");
    }
}
