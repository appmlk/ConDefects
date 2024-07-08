import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int D = sc.nextInt();
        int year = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();

        day++;

        if(day > D){
            month++;
            day = 1;
            if(month > M){
                year++;
                month = 1;
            }
        }

        System.out.println(year + " " + month + " " + day);


    }
}