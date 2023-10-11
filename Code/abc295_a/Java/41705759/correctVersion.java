
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
            if(s.equals("and") || s.equals("not") || s.equals("that") || s.equals("the") || s.equals("you"))
            {
                idx=0;
                break;
            }
        }
        if(idx==0) System.out.println("Yes");
        else System.out.println("No");
    }
}
