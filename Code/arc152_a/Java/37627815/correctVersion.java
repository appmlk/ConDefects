import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main{
	//static final int MAXN = (int)1e6;
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		/*int n = input.nextInt();
		int k = input.nextInt();
		k = k%4;
		int[] a = new int[n];
		for(int i = 0;i<n;i++){
			a[i] = input.nextInt();
		}
		int[] count = new int[n];
		int sum = 0;
		for(int tt = 0;tt<k;tt++){
			for(int i = 0;i<n;i++){
				count[a[i]-1]++;
			}
			for(int i = 0;i<n;i++){
				sum+= count[i];
			}
			for(int i = 0;i<n;i++){
				a[i] = sum;
				sum-= count[i];
			}
		}	

		for(int i = 0;i<n;i++){
			System.out.print(a[i] + " ");
		}*/
		
		int n = input.nextInt();
		int l = input.nextInt();
		int count = 0;
		int s = 0;
		for(int i = 0;i<n;i++){
			int a = input.nextInt();
			if(a == 2){
				if(s >= l){
					System.out.println("No");
					return;
				}
				else if(l-s == 2){
					s+= 2;
				}
				else{
					s+= 3;
				}


				if(s > l){
					System.out.println("No");
					return;
				}
			}
			else{
				s+= 2;
			}
		}	

		System.out.println("Yes");
	}
 
	public static int gcd(int a,int b){
		if(b == 0){
			return a;
		}
		return gcd(b,a%b);
	}
 
	/*public static int lcm(int a,int b){
		 return (a / gcd(a, b)) * b;
	}*/
}

class Pair implements Comparable<Pair>{
	int x;
	int y;

	Pair(int x,int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Pair p){
		return this.x-p.x;
	}
}



