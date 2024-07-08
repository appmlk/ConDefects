import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();

		for (int x = 0; x<=a; x++) {
			for (int y=0; y <=a-x; y ++) {
				for(int z = 0; z <=a - x - y; z ++) {
					System.out.println( x+" "+y+" "+z);
				}
				
				
			}
		}
		
		
	}

}