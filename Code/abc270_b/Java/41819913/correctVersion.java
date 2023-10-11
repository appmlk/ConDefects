import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int X=sc.nextInt();
		int Y=sc.nextInt();
		int Z=sc.nextInt();
		if((0<X&&X<Y)||(Y<0&&0<X)||(Y<X&&X<0)||(X<0&&0<Y)){
		    System.out.println(Math.abs(X));
		}else if((0<Y&&Y<X&&Y<Z)||(Z<Y&&X<Y&&Y<0)){
		    System.out.println(-1);
		}else{
		    if((Z>0&&X>0)||(Z<0&&X<0)){
		        System.out.println(Math.abs(X));
		    }else if((X<0&&0<Z)||(Z<0&&0<X)){
		        System.out.println(2*Math.abs(Z)+Math.abs(X));
		    }
		}
	}
}