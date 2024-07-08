import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	
	// Round-Robin Tournament
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		String[] s = new String[n];
		
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		for (int i = 0; i < s.length; i++) {
			s[i] = sc.next();
			int win = s[i].length() - s[i].replace("o", "").length();
			map.put(-win*10+(i+1),i+1);
		}		

		List<Entry<Integer, Integer>>list_entries = new ArrayList<Entry<Integer, Integer>>(map.entrySet());

		int i=0;
		for(Entry<Integer, Integer>entry:list_entries) {
			System.out.print(entry.getValue());
			i++;
			if(i==map.size()) {
				System.out.println();
			} else {
				System.out.print(" ");
			}
		}
				
	}
		
}