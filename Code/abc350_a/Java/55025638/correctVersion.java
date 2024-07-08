import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        String rep = s.replace("ABC", "");
        int castI = Integer.parseInt(rep);
        if(castI == 0 ||castI == 316 || castI >= 350){
            System.out.println("No");
        }else{
            System.out.println("Yes");
        }
    }
}