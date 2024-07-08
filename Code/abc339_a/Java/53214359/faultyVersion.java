import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] s = str.toCharArray(); 

        int counter = 0;
        for (int i = 0; i < s.length; i++){
            if (s[i] == '.'){
                counter = i;
            }
        }

        for (int j = counter; j < s.length; j++){
            System.out.print(s[j]);
        }
    }
}