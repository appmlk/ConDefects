import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Throwable{
		try(var s = new Scanner(System.in)){
			var n = s.nextInt();
			var Td = s.next();
			var TdLen = Td.length();
			var indices = new ArrayList<String>();
			for(var i = 1; i <= n; i++){
				var S = s.next();
				var SLen = S.length();
				var Si = 0; 
				var Tdi = 0;
				var diff = 0;
				while(Tdi < TdLen && Si < SLen){
					if(Td.charAt(Tdi) != S.charAt(Si)){
						diff++;
						if(diff > 1) break;
						if(TdLen < SLen) Tdi--;
						else if(TdLen > SLen) Si--;
					}
					Si++;
					Tdi++;
				}
				System.out.printf("Tdi: %d, TdLen: %d, Si: %d, SLen: %d%n",
					Tdi, TdLen, Si, SLen);
				if(diff <= 1 && Math.abs(TdLen - SLen) <= 1)
					indices.add("" + i);
			}
			System.out.println(indices.size());
			for(var j = 0; j < indices.size(); j++){
				if(j > 0) System.out.print(" ");
				System.out.print(indices.get(j));
			}
			System.out.println();
		}
	}
}
