import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        final int H = 2;
        final int W = 9;
        Integer[] sum = new Integer[2];
        sum[0]= 0;
        sum[1] = 0;

        
        for(int i = 0 ; i < H ; i++){
            if(i == 0){
                for(int j = 0; j < W; j++){
                    sum[0] += sc.nextInt();
                }
            }else{
                for(int j = 0; j < W-1 ; j++){
                    sum[1] += sc.nextInt();
                }
            }
        }

        if(sum[0] - sum[1]> 0){
            System.out.println(sum[0]-sum[1]+1);
        }else if(sum[1]-sum[0] == 0){
            System.out.println(1);
        }

       


       
    }
}