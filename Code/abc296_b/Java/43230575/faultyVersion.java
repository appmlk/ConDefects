import java.util.*;



public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] str = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8};

        for(int i = 0; i < 8; i++){
            String[] m = sc.nextLine().split("");
            for(int j = 0; j < 8; j++){
                if(m[j].equals("*")){
                    System.out.println(str[j] + "" + num[j]);
                    return;
                }
            }
        }
        
    }
}