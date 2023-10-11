import java.util.Scanner;
class Main {
	public static void main(String[] args) { 
		Scanner scanner=new Scanner(System.in);
		int n=scanner.nextInt();
		int[] a=new int[4];
		int x;
		int count=0;
		for(int i=0;i<n;i++){
		    x=scanner.nextInt();
		    for(int j=0;j<4;j++){
		        if(a[j]!=0){
		            a[j]+=x;
		            if(x>3){
		                count++;
		                a[j]=0;
		            }
		        }
		    }
		    if(x>3){
		        count++;
		    }else{
		        a[i%4]=x;
		    }
		}
		System.out.println(count);
	}
}