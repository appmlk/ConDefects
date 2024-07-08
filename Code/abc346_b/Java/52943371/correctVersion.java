import java.util.*;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int b = sc.nextInt();
		String wb = "wbwbwwbwbwbw", s = "";
		for(int i = 0; i < 20; i++) s += wb;
		int sl = s.length();
		for(int i = 0; i < sl; i++){
		  for(int j = i; j < sl; j++){
		    int wnum = 0, bnum = 0;
		    for(int k = i; k < j+1; k++){
		      if(s.charAt(k) == 'w') wnum++;
		      else bnum++;
		    }
		    if(wnum == w && bnum == b){
		      System.out.println("Yes");
		      return;
		    }
		  }
		}
		System.out.println("No");
	}
}
