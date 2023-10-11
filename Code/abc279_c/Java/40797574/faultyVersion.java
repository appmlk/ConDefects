import java.util.Scanner;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
	    Scanner get = new Scanner(System.in);
	    int H = get.nextInt();
	    int W = get.nextInt();
	    
		String[] S = new String[H];
		String[] T = new String[H];
		
		for(int i = 0; i < H; i++) S[i] = get.next();
		for(int i = 0; i < H; i++) T[i] = get.next();
		
		StringBuilder[] TS = new StringBuilder[W];
		StringBuilder[] TT = new StringBuilder[W];
		for(int i = 0; i < W; i++){
		    TS[i] = new StringBuilder();
		    TT[i] = new StringBuilder();
		}
		
		for(int i = 0; i < H; i++){
		    for(int j = 0; j < S[i].length(); j++){
		        TS[j].append(S[i].charAt(j));
		        TT[j].append(T[i].charAt(j));
		    }
		}
		
		Arrays.sort(TS);
		Arrays.sort(TT);
		
		boolean flag = true;
		
		for(int i = 0; i < W; i++){
		    if(TS[i].equals(TT[i])) {
		        flag = false;
		        break;
		    }
		}
		
		System.out.println((flag) ? "Yes" : "No");
		
		get.close();
	}
}