import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int T = scanner.nextInt();
		int sum=0;
		boolean v = false;
		int columncount[]=new int [N];
		int rowcount[]=new int [N];
		for(int i=0;i<N;i++) {
			columncount[i]=0;
			rowcount[i]=0;
		}
		int diagnolcount[]=new int [2];
		diagnolcount[0]=0;
		diagnolcount[1]=1;
		
		HashSet<Integer> Bingo = new HashSet<>();
		for(int i=0;i<T;i++) {
			int num = scanner.nextInt()-1;
			sum+=1;
			if(!Bingo.contains(num)) {
				Bingo.add(num);
				
				rowcount[num/N]++;
				if(rowcount[num/N]==N) {
					v =true;
					//System.out.println("a");
					break;
				}
				columncount[num%N]++;
				if(columncount[num%N]==N) {
					v =true;
					//System.out.println("b");
					break;
				}
				
	
				if(num/N==num%N) {
					diagnolcount[0]++;
					if(	diagnolcount[0]==N) {
					v =true;
					//System.out.println("c");
					break;
					}
				}
				
				if(N-1-num/N==num%N) {
					diagnolcount[1]++;
					if(	diagnolcount[1]==N) {
					v =true;
					//System.out.println("d");
					break;
					}
				}
				
				
				
			}
		}
		
		if(v) {
			System.out.println(sum);
		}else {
			System.out.println(-1);
		}
		
		
		scanner.close();
		
	
	}		
}





