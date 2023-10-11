import java.util.*;
public class Main {
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
      	int n = sc.nextInt();
      	int[] a = new int[n];
      	int s = 0;
      	for (int i = 0;i < n;i++) {
        	a[i] = sc.nextInt();
          	s ^= a[i];
        }
      	if (n % 2 == 1) System.out.println("Win");
      	else {
        	for (int i : a) {
            	if ((i ^ s) == 0) {
                	System.out.println("Win");
                  	return;
                }
            }
          	System.out.println("Lose");
        }
    }
}