import java.util.Scanner;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    sc.useDelimiter("");
    int n = sc.nextInt();
    String dush = sc.nextLine();
    String[] a = new String[n];
    for(int k = 0; k < n; k++){
      a[k] = sc.next();
    }

    int a_ch = 0;
    int b_ch = 0;
    int c_ch = 0;
    for(int k = 0; k < n; k++){
      if(a[k].equals("A")){
        a_ch = 1;
      }
      if(a[k].equals("B")){
        b_ch = 1;
      }
      if(a[k].equals("C")){
        c_ch = 1;
      }
      if(a_ch == 1 && b_ch == 1 && c_ch == 1){
        System.out.println(k + 1);
        break;
      }
    }
  }
}