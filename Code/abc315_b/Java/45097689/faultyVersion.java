import java.util.*;
public class Main
{
	public static void main(String[] args)
	{
	    Scanner s=new Scanner(System.in);
	    int n=s.nextInt();
	    int[] a=new int[n];
	    int sum=0;
	    for(int i=0;i<n;i++)
	    {
	        a[i]=s.nextInt();
	        sum=sum+a[i];
	    }	    
	    int mid=(sum+1)/2;
	    int x=0;
	    for(int i=0;i<n;i++)
	    {
	    	x=x+a[i];
	    	if(x>mid)
	    	{
	    		int y=x-mid;
	    		int z=a[i]-y;
    		    System.out.println((i+1)+" "+z);
    		    break;
	    	}
	    }
    }
}