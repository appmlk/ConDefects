import java.util.*;
public class Main
{
    public static void fullmoon(int N,int M,int P){
        int t;
        if(N>0&&M>0&&P>0&&N>=P){
            t = ((N-M)/P)+1;
        }else{
            t=0;
        }
        System.out.println(t);
    }
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int P = sc.nextInt();
		fullmoon(N,M,P);
	}
}
