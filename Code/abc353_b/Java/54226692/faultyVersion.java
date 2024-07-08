import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner s = new Scanner (System.in);
		
		int N,K,i,j;
		int count = 0;
		
		i=0;
		N = s.nextInt();
		K = s.nextInt();
		int A[] = new int[N];
		A[i] = s.nextInt();
		
		j=A[0];
//		System.out.println(j);
//		System.out.println(K);
		for(i=1; i<N ;i++) {
			A[i] = s.nextInt();
//			System.out.print(A[i] + "a ");
			
			if(K-j < A[i]) {  //K-j:残席数<次の乗る人数　発車する場合
				count=count+1;  //発車数
//				System.out.println(count);
				j=A[i];  //乗ってる人数
				
			}
			else if(K-j == A[i]){
				count++;
				j = 0;
			}
			else {
				j = j +A[i];  //乗ってる人数
//				System.out.print(j + "j ");
				
			}
			
		}
//		System.out.println("ループからぬけたよー");
		if(K-j==0) {
			System.out.println(count);
		}
		else {
			System.out.println(count+1);
		}
		
		
		s.close();
		
	}
}

