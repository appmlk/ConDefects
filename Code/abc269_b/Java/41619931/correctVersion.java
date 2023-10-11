import java.util.Scanner;
class Main {
	public static void main(String[] args) { 
		Scanner scanner=new Scanner(System.in);
		int a=Integer.MAX_VALUE;
		int b=Integer.MIN_VALUE;
		int c=Integer.MAX_VALUE;
		int d=Integer.MIN_VALUE;
		for(int i=0;i<10;i++){
		    String s=scanner.next();
		    for(int j=0;j<10;j++){
		        if(s.charAt(j)=='#'){
		            a=Math.min(a,i+1);
		            b=Math.max(b,i+1);
		            c=Math.min(c,j+1);
		            d=Math.max(d,j+1);
		        }
		    }
		}
		System.out.println(a+" "+b);
		System.out.println(c+" "+d);
	}
}