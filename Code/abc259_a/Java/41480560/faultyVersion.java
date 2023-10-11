import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        int x=scanner.nextInt();
        int t=scanner.nextInt();
        int d=scanner.nextInt();
        if(m>=x){
            System.out.println(t);
        } else if(m<x){
            int first=t-n*d;
            first+=d*m;
            System.out.println(first);
        }
    }
}