// Online Java Compiler
// Use this editor to write, compile and run your Java code online
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.io.*;

public class Main
{
  public static void main (String[]args)
  {
    Scanner sc = new Scanner (System.in);
    
    boolean f =  true ;
    int[] arry = new int[8] ; 
    
    for ( int a = 0 ; a < 8 ; a++  ){
        arry[a] = sc.nextInt();
    }
    for ( int a = 0 ; a < 8 ; a++  ){
        if (a<7 && arry[a] > arry[a+1]  ){
            f = false ;
        }
        if ( arry[a]%25 != 0  || arry[a]< 100 || arry[a] > 675  ){
            f = false ;
        }
    }
    
    System.out.println(f ? "Yes":"No");
    
  }
}

