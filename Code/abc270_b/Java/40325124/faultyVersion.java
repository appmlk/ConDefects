import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        if( y < x && z > y && y > 0){
            System.out.println("-1");
            return;
        }
        if( y < 0 && y > x  && y > z){
            System.out.println("-1");
            return;
        }
        if((x > 0 && y < 0)  || (x < 0 && y >0) ||  (y > x && x > 0) || (y < x && x < 0)){
            System.out.println(Math.abs(x));
            return;
        }
        if( (x > y && y > 0 && z < 0) ){
            System.out.println(2 * Math.abs(z) + x );
            return;
        }
        if( z > 0 &&  x < y && y < 0){
            System.out.println(z + 2 * Math.abs(x));
            return;
        }
        System.out.println(Math.abs(x));
    }
}
