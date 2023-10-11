import java.util.Scanner;

public class Main {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
int A = sc.nextInt();
int B = sc.nextInt();
double d = (double)B / A;
d = Math.round(d * 10000) / 10000.0; // 小数点第4位で四捨五入
System.out.printf("%.3f", d);
System.out.println();
}
}