import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int r=sc.nextInt();
		int c=sc.nextInt();
		if(r==1||c==1||r==15||c==15){
		    System.out.println("black");
		}else if(r==2||r==14||c==2||c==14){
		    System.out.println("white");
		}else if(r==3||c==13||c==3||r==13){
		    System.out.println("black");
		}else if(r==4||c==12||r==12||c==4){
		    System.out.println("white");
		}else if(r==5||c==11||r==11||c==5){
		    System.out.println("black");
		}else if(r==6||c==10||r==10||c==6){
		    System.out.println("white");
		}else if(r==7||c==9||r==9||c==7){
		    System.out.println("black");
		}else{
		    System.out.println("white");
		}
	}
}