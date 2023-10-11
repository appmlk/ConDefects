 import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();
            char[] work = new char[text.length()];
        for(int i = 0; i < text.length(); i++){
            work[i] = text.charAt(i);
        }
        if(work[0] == work[1]){
           if(work[1] == work[2]){
             System.out.println(-1);
           }else{
            System.out.println(work[2]);
           }
        }else{
          if(work[1] == work[2]){
            System.out.println(work[0]);
          }else{
            if(work[0] == work[2]){
              System.out.println(work[1]);
            }else{
              System.out.println(-1);
            }
          }
        }
        }
    }