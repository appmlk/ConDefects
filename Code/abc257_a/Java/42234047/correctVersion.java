import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
class Main {
	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int n = sc.nextInt();
        String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(n % c == 0){
        System.out.println(a.charAt(n / c - 1) + "");
            }else {
            System.out.println(a.charAt(n / c) + "");
            }
            } 
        }