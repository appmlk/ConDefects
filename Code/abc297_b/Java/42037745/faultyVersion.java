import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		boolean evenOddFlag=false;
		boolean existR=false;
		
		if(str.indexOf("B")%2==0) {
			if(str.lastIndexOf("B")%2 !=0) {
				evenOddFlag=true;
			}
		}else {
			if(str.lastIndexOf("B")%2 ==0) {
				evenOddFlag=true;
			}
		}
		
		String rr=str.substring(str.indexOf("B"),str.lastIndexOf("B")+1);
		
		if(rr.indexOf("K")!=-1) {
			existR=true;
		}
		
		if(evenOddFlag&&existR) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		sc.close();
	}

}