import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Array;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        String str1 = s.substring(0,2);
        String str2 = s.substring(3,6);

        if(Integer.parseInt(str2) < 350 && Integer.parseInt(str2) !=316){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }
}