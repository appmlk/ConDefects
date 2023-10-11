import java.util.Scanner;
class Main {
	public static void main(String[] args) { 
		Scanner scanner=new Scanner(System.in);
		int a=scanner.nextInt();
		int b=scanner.nextInt();
		int c=scanner.nextInt();
		int d=scanner.nextInt();
		if(a<c){
		    System.out.println("Takahashi");
		} else if(a>c){
		    System.out.println("Aoki");
		} else if(a==c){
		    if(b>d){
		        System.out.println("Aoki");
		    } else if(b<d){
		        System.out.println("Takahashi");
		    }
		}
	}
}