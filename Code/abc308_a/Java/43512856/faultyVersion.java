import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] input = new int[8];
        int tmp = 0;
        String result = "Yes";
        for(int i=0; i<8; i++){
            input[i] = sc.nextInt();
            if(input[i] < 100 || input[i] > 675){
                result = "No";
                break;
            }
            if(input[i]%25 != 0){
                result = "No";
                break;
            }
            if(input[i] <= tmp){
                result = "No";
                break;
            }
            tmp = input[i];
        }
        System.out.println(result);
    }
}
