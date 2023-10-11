import java.util.*;
public class Main
{
    public static String Sandwich(String str){ //A100000K
        if(str.length() != 8){
            return "No";
        }
        
        int upperCase = 0;
        int numberCount = 0;
        
        for(int i = 0 ; i < str.length(); i++){
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
              if(i==0 || i == 7)  upperCase++;
            }
            
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                numberCount = numberCount*10 + str.charAt(i) - '0';
            }  
        }
       System.out.println(numberCount);	
        if(upperCase == 2 && (numberCount >= 100000 && numberCount <= 999999)) return "Yes";
        return "No";
    }
    
	public static void main(String[] args) {
		Scanner san = new Scanner(System.in);
		String str= san.next();		
	    System.out.println(Sandwich(str));	
	}
}








