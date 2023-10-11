import java.util.Hashtable;
import java.util.Scanner;

class Main  {
    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);
        String ns = s.nextLine();
        s.close();
        
        for (int i=0; ns.length() > i; i++) {
            if (Character.isUpperCase(ns.charAt(i))) {
                System.out.println(i);
                break;
            }   
        }
    }
} 