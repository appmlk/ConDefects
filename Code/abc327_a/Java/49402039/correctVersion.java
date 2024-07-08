import java.util.*;
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        if(s.contains("ab") || s.contains("ba")){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}