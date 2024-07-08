import java.util.Scanner;
class Main{
    public static void main(String[] args){
      
      Scanner sc = new Scanner(System.in);
    //   String n = sc.next();
    //   String[] arr = new String[];
    //   int cnt =0;
      
      for(int i = 0;i<1;i++){
        String a = sc.next();
        if(a.equals("Atcoder")){
            String b = sc.next();
            if(b.equals("Land")){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");

            }
        }
        else{
            System.out.println("No");
        }
      }      
    }
  }