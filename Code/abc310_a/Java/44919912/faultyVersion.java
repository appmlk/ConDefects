import java.util.Scanner;

public class Main {

    public static void main(String args[]){
    
        //Using booleans when dealing with yes/no, true/false, 1/0.. , minimising no of variables
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int orgPrice = sc.nextInt();
        int discPrice = sc.nextInt();
        int minDPrice = sc.nextInt();

        int[] dishesPrice = new int[N];
        for(int i = 1; i < N-1; i++){
            dishesPrice[i] = sc.nextInt();
            if(dishesPrice[i] < minDPrice) minDPrice = dishesPrice[i];
        }

        if(orgPrice > (minDPrice + discPrice)) System.out.println(minDPrice + discPrice);
        else System.out.println(orgPrice);

    }

}
