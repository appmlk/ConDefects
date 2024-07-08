import java.util.*;
public class Main {
    
	public static void main(String[] args){
		try (Scanner sc = new Scanner(System.in)) {
            int N = sc.nextInt();
            int[] numArray = new int[N];
            int max = 0;
            for(int i = 0;i<N;i++){
                int num = sc.nextInt();
                numArray[i] = num;
                if(i > 1){
                    if(numArray[i-1] > num){
                        max= i-1;
                    }
                }
            }
            int maxNum = numArray[max];
            int maxNum2 = 0;
            int max2 = 0;
            for(int i=max;i<N;i++){
                int num = numArray[i];
                if(num < maxNum){
                    if(num > maxNum2){
                        maxNum2 = num;
                        max2 = i;
                    }
                }
            }
            numArray[max2] = maxNum;
            numArray[max] = maxNum2;
            int temp = 0;


            for(int i=max+1;i<N;i++){
                for(int j=N-1;j>=i;j--){
                    if(numArray[j] > numArray[j-1]){
                        temp = numArray[j];
                        numArray[j] = numArray[j-1];
                        numArray[j-1] = temp; 
                    }
                }
            }

            for(int i=0;i<N;i++){
                if(i!=0){
                    System.out.print(" ");
                }
                System.out.print(numArray[i]);

            }System.out.println("");
        }
    }
}
