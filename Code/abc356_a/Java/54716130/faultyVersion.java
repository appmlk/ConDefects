import java.util.Scanner;
public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] suretu = new int[N + 1];
		for(int i = 1;i <= N; i++){
		  suretu[i] = i;
		}
		int L = sc.nextInt();
		int R = sc.nextInt();
		
		int k = R;
		
		for(int i = L;i <= R;i++){
		  suretu[i] = k;
		  k--;
		}

        for(int i = 1;i <= N;i++){
            System.out.print(suretu[i] + "");
        }
		
	}
}