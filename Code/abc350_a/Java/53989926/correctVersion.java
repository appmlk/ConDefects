
public class Main {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        String s = sc.next();
        int num = Integer.parseInt(s.substring(3));
        
        if(num == 316 || num >= 350 || num <= 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
        
    }
}
