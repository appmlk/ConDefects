import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String str1;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str1 = br.readLine();
		}catch (Exception e) {
			System.out.println("Input Error!!");
			return;
		}
		String[] sa = str1.split(" ");
		ArrayList<Integer> il = new ArrayList<Integer>();
		for(String s:sa) {
			il.add(Integer.parseInt(s));
		}
		aGeneralizedABC(il);
	}
	private static void aGeneralizedABC(ArrayList<Integer> il) {
		String abcString = "ABCDEFGHIJKLMNOPQURSTUVWXYZ";
		String ans = abcString.substring(0,il.get(0));
		System.out.println(ans);
	}
}
