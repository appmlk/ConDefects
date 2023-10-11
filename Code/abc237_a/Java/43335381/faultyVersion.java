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
        long n = sc.nextLong();
        long a = (long)1 << 31;
        if(n >= a || n <= -a){
            System.out.println("No");
        }else{
            System.out.println("Yes");
        }
        }
            }