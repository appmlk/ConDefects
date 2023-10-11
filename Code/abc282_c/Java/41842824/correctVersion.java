import java.util.*;
class Main {
	public static void main(String[] args) { 
		Scanner scanner =new Scanner(System.in);
		int n=scanner.nextInt();
		String s=scanner.next();
		int cnt=0;
		
		for(int i=0;i<n;i++){
		    if(s.charAt(i)==('"'))cnt++;
		    if(s.charAt(i)!=','){
		        System.out.print(s.charAt(i));
		    } else{
		        if((cnt & 1)==1){
		            System.out.print(",");
		        } else{
		            System.out.print(".");
		        }
		    }
		}
	}
}