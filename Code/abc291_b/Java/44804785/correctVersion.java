import java.util.*;
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int X[]=new int[5*N];
        double sum=0;
        for(int i=0;i<5*N;i++)
        {
            X[i]=sc.nextInt();
            sum+=X[i];
            
        }
        
        for(int j=0;j<N;j++)
        {
            
            int max_pos=0,min_pos=0;
            int max=-2;
            int min=101;
            for(int k=0;k<5*N;k++)
            {
                if(X[k]==-1)
                continue;
                if(X[k]>max)
                {max=X[k];
                  max_pos=k;
                }
                if(min>X[k])
                {min=X[k];
                    min_pos=k;
                }
            }
            sum=sum-max-min;
            X[max_pos]=-1;
            X[min_pos]=-1;
            
        }
        double avg=sum/(3*N);
        System.out.println(avg);
    }
}