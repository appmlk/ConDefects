import java.util.*;
import java.math.*;

class node
{
    int x,y;

    public node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n=sc.nextInt(),m=sc.nextInt();
        int[] a = new int[n];
        Long ans=0L;
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
            ans=ans+a[i];
        }
        int[] b = new int [m];
        int[] c = new int [m];
        for(int i=0;i<m;i++)
            b[i]=sc.nextInt();
        for(int i=0;i<m;i++)
            c[i]=sc.nextInt();
        List<node> li = new ArrayList<>();
        for(int i=0;i<m;i++)
            li.add(new node(b[i],c[i]));
        li.sort((x,y)->{
            if(x.x!=y.x)
                return y.x-x.x;
            else
                return y.y-y.x;
        });
        Arrays.sort(a);
        PriorityQueue<Integer> qu= new PriorityQueue<>();
        int pos=0;
        int p=li.size();
        for(int i=n-1;i>=0;i--)
        {
            if(pos>=p)
                break;
            int pp=pos;
            while(pos<p && li.get(pos).x>a[i])
            {
                pos++;
            }
            if(!qu.isEmpty()) {
                for (int j = pp; j < pos; j++)
                    if (li.get(j).y > qu.peek()) {
                        // ans =ans - (li.get(j).y - qu.peek());
                        qu.add(li.get(j).y);
                        qu.poll();
                    }
            }
            if(pos==p)
                continue;
            qu.add(li.get(pos).y);
            // ans=ans-li.get(pos).y;
            pos++;
        }
        while(!qu.isEmpty())
        {
            ans-=qu.peek();
            qu.poll();
        }
        System.out.println(ans);
    }
}
/*
10 5
9 7 1 5 2 2 5 5 7 6
7 2 7 8 2
3 2 4 1 2


3 3
4 1 1
4 4 3
2 1 3

3 3
4 1 1
4 2 1
1 2 1

 */