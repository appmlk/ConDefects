import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);

        var s=new StringBuilder(scan.next());
        var t=new StringBuilder(scan.next());

        scan.close();

        var can=false;
        var n=s.length();
        for(int i=0;i<n-1;i++) {
            var si=s.charAt(i);
            var si1=s.charAt(i+1);
            s.setCharAt(i, si1);
            s.setCharAt(i+1, si);

//            System.out.println(s);
            if(s.toString().equals(t.toString())) {
            	can=true;
            }

            s.setCharAt(i, si);
            s.setCharAt(i+1, si1);
        }

        if(s.toString().equals(t.toString()) || can) System.out.println("Yes");
        else System.out.println("No");
    }

}
