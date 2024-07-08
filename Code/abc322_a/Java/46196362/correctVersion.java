import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int limit = sc.nextInt();
        String str = sc.next();
//        System.out.println(str);
        if(str.indexOf("ABC") != -1){
            System.out.println(str.indexOf("ABC")+1);
//            System.out.println("yes");
        }
        else{
            System.out.println(-1);
        }
//        System.out.println(str);
    }
}