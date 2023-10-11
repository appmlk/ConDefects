import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        
        char[] letters = input.toCharArray();
        int counter = letters.length-1;
        
        long ans = 0;
        
        for (int i = 0; i < letters.length; i++)
        {
            char set = letters[i];
            int ascii = set;
            ans += (ascii - 64) * (Math.pow(26,counter));
            counter--;
        }
        
        System.out.println(ans);
        
    }
}