import java.util.*;
public class Main
{     
	public static void main(String[] args)
    {        
		Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int k = sc.nextInt();

        String s = sc.next();
        String[] win = s.split("");
        int hit = 0;

        for(int i = 0 ; i < win.length ; i ++)
        {
            if(win[i].equals("o"))
            {
                hit++;
            }

            if(hit > k)
            {
                win[i] = "x";
            }
        }

        for(int i = 0 ; i < win.length ; i ++)
        {
            System.out.println(win[i]);
        }
    }
}