
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int matrix[][] = new int[9][9];

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        //col
        for(int i = 0; i < 9; i++) {
            Set<Integer> set = new HashSet<Integer>();
            for(int j = 0; j < 9; j++) {
                set.add(matrix[i][j]);
            }

            if(set.size() != 9) {
                System.out.println("No");
                return;
            }
        }

        //row
        for(int i = 0; i < 9; i++) {
            Set<Integer> set = new HashSet<Integer>();
            for(int j = 0; j < 9; j++) {
                set.add(matrix[j][i]);
            }

            if(set.size() != 9) {
                System.out.println("No");
                return;
            }
        }

        //square
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Set<Integer> set = new HashSet<Integer>();
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l < 3; l++) {
                        set.add(matrix[k][l]);
                    }
                }
                if(set.size() != 9) {
                    System.out.println("No");
                    return;
                }
            }
        }

        System.out.println("Yes");
    }
}
