import java.util.Scanner;

public class Main
{
    public static void main(String [] args)
    {
         class solve
        {
            long bridge,connection;
            solve(Scanner in)
            {
                this.bridge=in.nextLong();
                this.connection=in.nextLong();
                long []diff =new long[(int)bridge+2];
                long last=-1,ans=0;

                for(long i=0;i<connection;i++)
                {
                    long now=in.nextLong();
                    long mini=last,maxi=now;
                    if(last!=-1)
                    {
                        if(last>now)
                        {
                            mini=now;
                            maxi=last;
                        }
                        long cost1=maxi-mini;
                        long cost2=mini+bridge-maxi;
                        ans+=Math.min(cost1,cost2);
                        long seperate=Math.abs(cost1-cost2);
                        if(cost1>cost2)
                        {
                            diff[1]+=seperate;
                            diff[(int)mini]-=seperate;
                            diff[(int)maxi]+=seperate;
                        }
                        else
                        {
                            diff[(int)mini]+=seperate;
                            diff[(int)maxi]-=seperate;
                        }
                    }
                    last=now;
                }
                long total=(long)1e18;
                for(long i=1;i<=bridge;i++)
                {
                    diff[(int)i]+=diff[(int)i-1];
                    total=Math.min(total,diff[(int)i]);                    
                }
                System.out.println(ans+total);
            }
        }
        Scanner in=new Scanner(System.in);
        solve n=new solve(in);
        // close(in);
    }
}
