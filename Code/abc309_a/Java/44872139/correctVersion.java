import java.util.*;
import java.lang.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int A = sc.nextInt();
    int B = sc.nextInt();
    boolean F = false;

    // if(A==1 && B==2) F=true;
    // if(A==2 && B==3) F=true;
    // if(A==4 && B==5) F=true;
    // if(A==5 && B==6) F=true;
    // if(A==7 && B==8) F=true;
    // if(A==8 && B==9) F=true;

    // if(F) System.out.println("Yes");
    // else System.out.println("No");

    if(B-A == 1 && A % 3 != 0) System.out.println("Yes");
    else System.out.println("No");  
  }
}