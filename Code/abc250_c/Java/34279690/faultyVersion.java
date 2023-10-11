import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();

		int[] ball	= new int[n];
		int[] pos = new int[n];
		
		for(int i =0; i < n; i++) {
			ball[i] = i;
			pos[i] = i;
		}
		
		for(int i = 0; i < q; i++) {
			int xi = sc.nextInt();
			xi--;
			
			int index = pos[xi];
			int j = index+1;
			if(j == n) {
				j = index-1;
			}
			int temp = ball[j];
			int yi = ball[j];
			ball[j] = ball[index];
			ball[index] = temp;
			swap(pos, xi, yi);
		}
		System.out.println(Arrays.toString(ball));
		
		for (int k = 0; k < n; k++) {
			if (k != n - 1) {
				System.out.print(ball[k]+1 + " ");
			} else {
				System.out.println(ball[k]+1);
			}
		}

	}
	public static void swap(int[] arr, int i, int j) {
	    arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
	}

	
}
