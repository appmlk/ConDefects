import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String output = Integer.toHexString(n).toUpperCase();
        if(output.length()==1)output="0"+output;
        System.out.println(output);
        sc.close();
    }
}