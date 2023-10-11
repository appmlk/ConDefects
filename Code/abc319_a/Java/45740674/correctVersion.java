

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String S = scanner.nextLine();

        String[] namesStrings = {"tourist","ksun48","Benq","Um_nik","apiad","Stonefeang","ecnerwala","mnbvmar","newbiedmy","semiexp"};
        int[] rateArray = {3858,3679,3658,3648,3638,3630,3613,3555,3516,3481};
        
        for(int i = 0;i<namesStrings.length;i++){
            if(S.equals(namesStrings[i])){
                System.out.println(rateArray[i]);
            }
        }

        scanner.close();
    }
}
