import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.math.BigInteger;
import java.util.Comparator;
 
class Main {
	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        if(a > 42){
            a++;
        }
        String ans = "AGC0" + String.format("%02d", a);
        System.out.println(ans);
    }
}
        