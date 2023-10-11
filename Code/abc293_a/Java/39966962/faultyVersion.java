import java.util.*;

class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String bef = sc.nextLine();
        for (int i = 0; 2 * i <= bef.length(); i+=2){
            System.out.print(bef.charAt(i+1));
            System.out.print(bef.charAt(i));
        }
    }
}
