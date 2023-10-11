

import java.util.*;
 
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        double radi = Math.atan2(a, b);
        double cos = Math.cos(radi);
        double sin = Math.sin(radi);
        System.out.printf("%.12f, %.12f",sin,cos);
        sc.close();
    }
}