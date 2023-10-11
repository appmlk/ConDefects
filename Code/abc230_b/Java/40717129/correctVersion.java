import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Scanner scan = new Scanner(System.in);
        String S3 = scan.next();
    
        // S3をsplitで分割して1文字ずつ格納
        String[] strArray = S3.split("");
        
        boolean Maru = false;
        boolean Batu1 = false;
        boolean Batu2 = false;
        
        //System.out.println(strArray[0]);
        
        if (strArray[0].equals("o")) {
            Maru = true;
        }
        else {
            Batu1 = true;
        }
        
        String next_str = "";
         
        for (int i = 1; i < strArray.length; i++) {
            next_str = strArray[i]; 
            
            if (Maru) {
                if (next_str.equals("x")) {
                    Maru = false;
                    Batu1 = true;
                }
                else {
                    System.out.println("No");
                    System.exit(0);
                }
            }
            
            else if (Batu1) {
                if (next_str.equals("x")) {
                    Batu1 = false;
                    Batu2 = true;
                }
                else {
                    if (i != 1) {
                    System.out.println("No");
                    System.exit(0);
                    }
                    else {
                        Batu1 = false;
                        Maru = true;
                    }
                }
            }
            
            else if (Batu2) {
                if (next_str.equals("o")) {
                    Batu2 = false;
                    Maru = true;
                }
                else {
                    System.out.println("No");
                    System.exit(0);
                }
            }
            
            
        }
      
        System.out.println("Yes");   
            
    }    
    
}
