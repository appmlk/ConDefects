import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PrintWriter output = new PrintWriter(System.out);
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		char[] c = sc.next().toCharArray();
		int Q = sc.nextInt();
		char[] order = new char[Q];
		char[] lastOrderPreBS = new char[N];
		char[] lastOrder = new char[N];
		char[] result = new char[N];
		
		int[][] table = new int[Q][2];
		for(int i=0;i<Q;i++) {
			table[i][0] = sc.nextInt();
			table[i][1] = sc.nextInt();
			order[i] = sc.next().toCharArray()[0];
		}
		
		int lastBS = 0;
		int BS = 0;
		for(int i=Q-1;i>=0;i--) {
			if(table[i][0]==2||table[i][0]==3) {
				lastBS = i;
				BS = table[i][0];
				break;
			}
		}
		//BS前
		for(int i=0;i<lastBS;i++) {
			if(table[i][0]==1)
				lastOrderPreBS[table[i][1]-1] = order[i];
		}
		//BS後
		for(int i=lastBS+1;i<Q;i++) {
			if(table[i][0]==1)
				lastOrder[table[i][1]-1] = order[i];
		}
		
		//BS前,初期融合
		result = lastOrderPreBS;
		for(int i=0;i<N;i++) {
			if(result[i]==0) result[i] = c[i];
		}
		
		//BS前にBS適用
		if(BS==2) {
			for(int i=0;i<N;i++) {
				if(result[i]>=65 && result[i]<=90)
					result[i] += 32;
			}
		}
		else if(BS==3) {
			for(int i=0;i<N;i++) {
				if(result[i]>=97 && result[i]<=122)
					result[i] -= 32;
			}
		}

		//融合、初期融合
		for(int i=0;i<N;i++) {
			if(lastOrder[i]!=0)
				result[i] = lastOrder[i];
		}
		
		for(int i=0;i<N;i++) {
			output.print(result[i]);
		}
		output.print("\n");
			
		output.flush();
		sc.close();
	}
}