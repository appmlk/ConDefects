
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String S = scanner.nextLine();
        boolean[] flag = new boolean[10];

        for(int i = 0;i<10;i++){
            flag[i] = true;
        }

        for(int i = 0;i<9;i++){
            flag[Character.getNumericValue(S.charAt(i))] = false;
        }

        for(int i = 0;i<10;i++){
            if(flag[i]==false){
                System.out.println(i);
            }
        }

        scanner.close();
    }
}
