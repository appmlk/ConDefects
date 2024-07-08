import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int sEnd = Integer.parseInt(s.substring(3));
        for(int i = 0; i < 350; i++){
            if(i == 316) continue;
            if(sEnd == i){
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
        return;
    }
}
