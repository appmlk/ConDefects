import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new FileReader(new File("input.txt")));
        
        int r = sc.nextInt();
        int c = sc.nextInt();

        if (Math.max(Math.abs(r-8), Math.abs(c-8)) % 2 == 0) System.out.println("white");
        else System.out.println("black");

        sc.close();
    }
}
