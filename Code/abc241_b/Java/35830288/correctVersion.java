
import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String row1 = scan.nextLine();
		String row2 = scan.nextLine();
		String row3 = scan.nextLine();
		
		String n = row1.split(" ")[0];
		String m = row1.split(" ")[1];
		
		String[] arr1 = row2.split(" ");
		String[] arr2 = row3.split(" ");
		int count = 0;
		
		ok : for( int i = 0 ; i < arr2.length ; i++) {
			for( int j = 0 ; j<arr1.length ; j++) {
				
				if( arr2[i].equals(arr1[j])) {
					count++;
					arr1[j] = "";
					continue ok;
				}
			}
		}
		
		if(count==Integer.parseInt(m)) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		
	}

}
