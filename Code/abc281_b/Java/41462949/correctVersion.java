import java.util.*;
public class Main{
	public static void main(String[] args) {
	    Scanner sc=new Scanner(System.in);
	    String S=sc.next();
	    int l=0;
	    if(S.length()!=8){
	        System.out.println("No");
	    }
	    else{
	   for(int i=0;i<=7;i++){
	       if(i==0||i==7){
	           int k=(int)S.charAt(i);
	           if(65<=k&&k<=90){
	               l++;
	           }
	           else{
	               System.out.println("No");
	               break;
	           }
	       }else if(i==1){
	           int M=(int)S.charAt(i);
	           if(M>=49&&M<=57){
	               l++;
	           }else{
	               System.out.println("No");
	               break;
	           }
	       }else{
	           int M=(int)S.charAt(i);
	           if(M>=48&&M<=57){
	               l++;
	           }else{
	               System.out.println("No");
	               break;
	           }
	       }
	   }
	   if(l==8){
	       System.out.println("Yes");
	   }
	    }
	}
}