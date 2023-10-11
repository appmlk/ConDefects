import java.util.*;
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double A = sc.nextDouble();
        double B = sc.nextDouble();
        double C = sc.nextDouble();
        double X = sc.nextDouble();
        sc.close();
       if(A>=X){
        System.out.println(String.format("%.12f", (double)1));
       }
       else{
        if(B>X){
            System.out.println(String.format("%.12f", (C/(B-A))));
        }
        else{
            System.out.println(String.format("%.12f", (double)0));
        }
       }
    }
}