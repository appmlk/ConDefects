

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       int N ;
       Scanner sc = new Scanner(System.in);
       N=sc.nextInt();
       sc.nextLine();
       ArrayList<String> list = new ArrayList<>();
       int n=N;
       int sum = 0;
       while (N-->0){
           String s = sc.nextLine();
           int t = s.indexOf(" ");
           String substring = s.substring(0, t);

           list.add(substring);
           String ss = s.substring(t+1);

           sum+=Integer.parseInt(s.substring(t+1));
       }
         list.sort(new Comparator<String>() {
             @Override
             public int compare(String o1, String o2) {
                 int length = Math.min(o1.length(),o2.length());
                 for (int i = 0; i < length; i++) {
                     int j = o1.charAt(i)-o2.charAt(i);
                     if (j!=0){
                     return j;
                     }
                 }
                 return o1.length()-o2.length();
             }
         });
        int i = sum%n;
        System.out.println(list.get(i));
    }


}
