import java.util.*;

public class Main
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        // 人数
        int n = sc.nextInt();

        // イベント数
        int q = sc.nextInt();

        // 呼ばれてない人の中で最小
        Integer minWeit = 1;

        // 呼ばれたけど行ってない人
        Set<String> call = new LinkedHashSet<>();
        
        for (int i=1; i<=q; i++)
        {
            //  イベントの種類
            int e = sc.nextInt();
            // System.out.println("event"+e);
            if (e == 1)
            {
                call.add(minWeit.toString());
                minWeit++;
            }
            else if (e == 2)
            {
                // 行く人
                String p = sc.next();
                
                call.remove(p);
                
            }
            else
            {
                Iterator it = call.iterator();
                String s = it.next().toString();
                System.out.println(s);
            }
        }
    }

}
