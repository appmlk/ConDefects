import java.util.*;
class Main {
    static int x;
    static int y;
	public static void main(String[] args){
	    Scanner scanner = new Scanner(System.in);
	    int n=scanner.nextInt();
	    x=scanner.nextInt();
	    y=scanner.nextInt();
	    long ans=cals(n,true);
	    System.out.println(ans);
	    
	}
	public static int cals(int level,boolean isred){
	    if(level==1){
	        return isred ? 0 : 1;
	    }
	    if(isred){
	        return cals(level-1,true)+cals(level,false)*x;
	    } else{
	        return cals(level-1,true)+cals(level-1,false)*y;
	    }
	}
}