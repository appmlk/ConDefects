import java.util.*;
class Main{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		ArrayList<Integer>ar=new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			ar.add(sc.nextInt());
		}
		Collections.sort(ar);
		int j=1;
		int f=0;
		for(int i=0;i<n;i++)
		{
			while((j+1)<n && ar.get(j+1)-ar.get(i)<=x)
				j++;
			if(ar.get(j)-ar.get(i)==x)	
				f=1;
		}		
		if(f==1)
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}
