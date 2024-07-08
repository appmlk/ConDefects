import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String S = scanner.nextLine();
		long num=(long)S.length()*(S.length()-1)/2;
		HashMap<Character, Integer>A=new HashMap<Character, Integer>();
		for(int i=0; i<S.length();i++) {
			char s = S.charAt(i);
			if(A.containsKey(s)) {
				A.put(s, A.get(s)+1);
			}else {
				A.put(s,1);
			}
		}
	boolean v = false;
	//System.out.println(S.length());
	//System.out.println(num);

	//System.out.println(1000000*(1000000-1)/2);
		
		for(Character i:A.keySet()) {
			//System.out.print(i);
			//System.out.print(A.get(i));
			num-=(A.get(i)*(A.get(i)-1)/2);
			if(A.get(i)>1) {
				v = true;
			}
			//System.out.println(num);
		}
		//System.out.println();
		
		if(v==true) {
			num+=1;
		}
		
		System.out.println(num);
		
		scanner.close();
}
	
}


