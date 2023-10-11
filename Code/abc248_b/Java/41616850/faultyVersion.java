import java.util.Scanner;
class Main {
	public static void main(String[] args) { 
		Scanner scanner=new Scanner(System.in);
		int a=scanner.nextInt();
		int b=scanner.nextInt();
		int k=scanner.nextInt();
		int count=0;
		if(a>=b){
		    System.out.println(0);
		    return;
		}
		while(true){
		    a*=k;
		    count++;
		    if(a>=b){
		        System.out.println(count);
		        break;
		    }
		}
	}
}