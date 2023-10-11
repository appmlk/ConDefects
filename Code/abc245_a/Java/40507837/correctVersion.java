import java.util.Scanner;

class Main{
 public static void main(String[] args){
   Scanner scan = new Scanner(System.in);
   int a = scan.nextInt();
   int b = scan.nextInt();
   int c = scan.nextInt();
   int d = scan.nextInt();
   
   String name;
   if( a > c ){
     name = "Aoki";
   } else if ( a == c && b > d ){
     name = "Aoki";
   } else {
    name = "Takahashi";
   }
   
   System.out.println(name);
 }
}