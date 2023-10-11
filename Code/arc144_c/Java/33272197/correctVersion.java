
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {

			int N = sc.nextInt();
			int K = sc.nextInt();
			int[] test = new int[N];
			int testindex = 0; 
			if(N/2<K) {
				System.out.print(-1);
			}else {
				//
				int fixketa = 0;
				int freeketa = N;
				while(true) {
					if(freeketa-K*2>=K*2) {
						for(int i=0; i<K*2;i++) {
							test[testindex]=(i+K)%(K*2)+1+fixketa;
							testindex++;
						}
						freeketa -= (K*2);
						fixketa += (K*2);
					}else {
						for(int i=0; i<freeketa;i++) {
							test[testindex]=(i+K)%freeketa+1+fixketa;
							testindex++;
						}
						break;
					}
					
				}
				if(N%K!=0&&(N/K)%2==1) {
					int[] tmpleft = new int[N%K];
					int[] tmpright= new int[N%K];
					for(int i=0; i<N%K; i++) {
						tmpleft[i]=test[N-2*K-(N%K)+i];
						tmpright[i]=test[N-K+i];
					}
					for(int i=0; i<K-(N%K); i++) {
						test[N-K+i]=test[N-K+i+(N%K)];
					}
					for(int i=0; i<N%K; i++) {
						test[N-2*K-(N%K)+i]=tmpright[i];
						test[N-(N%K)+i]=tmpleft[i];
					}
				}
				
				for(int i=0; i<N; i++) {
					System.out.print(test[i]+" ");
				}
			}
		}
	}
}
