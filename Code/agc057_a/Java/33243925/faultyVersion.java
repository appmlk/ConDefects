

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0;i<n;i++)
		{
			long l = sc.nextLong();
			long r = sc.nextLong();
			
			int d1 = Long.toString(l).length();
			int d2 = Long.toString(r).length();
			
			long head=r;
			while(head>=10)head/=10;
			
			if(d1==d2)
			{
				System.out.println((r-l+1));
				continue;
			}
			
			//System.out.println("H " + head);
			
			if(head==1)
			{
				long lef1 = head;
				for(int k=0;k<d2-1;k++)
					lef1*=10;
				//System.out.println("temp " + lef);
				
				lef1=r-lef1+1;
				
				long lef2=(r/10)+1;
				long lef = Math.max(lef1,lef2);
				lef = Math.max(l,lef);
				
				System.out.println((r-lef+1));
				
				
			}
			else
			{
				long lef = head;
				for(int k=0;k<d2-1;k++)
					lef*=10;
				//System.out.println("temp " + lef);
				lef = Math.max(l,lef);
				System.out.println((r-lef+1));
			}
			
		}

	}

}
