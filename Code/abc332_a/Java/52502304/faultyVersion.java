import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int num = sc.nextInt();
    	int kijun = sc.nextInt();
    	int souryou = sc.nextInt();
    	int sum = 0;
    	for(int i= 0;i<num;i++) {
    		int price = sc.nextInt();
    		int kosuu = sc.nextInt();
    		sum += price * kosuu;
    	}
    	if(kijun <= sum) {
    		sum += souryou;
    	}
    	System.out.println(sum);
    }
}
