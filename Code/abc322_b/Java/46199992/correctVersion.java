import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        String sa=sc.next();
        String sb=sc.next();
        boolean pre=false;
        boolean su=false;
        sc.close();
        if(sb.endsWith(sa)){
            su=true;
        }
        if(sb.startsWith(sa)){
            pre=true;
        }
        if(su&&pre){
            System.out.println(0);
        }else if(pre&&!su){
            System.out.println(1);
        }else if(su&&!pre){
            System.out.println(2);
        }else{
            System.out.println(3);
        }
       
    }
}
