import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] srgs){
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine());
        String[] stdata = new String[N];
        for(int i=0;i<N;i++){
            stdata[i] = scan.nextLine();
            System.out.println(stdata[i]+" :2");
        }

        for(int i=0;i<N;i++){
            String search = stdata[i];
            for(int j=0;j<N;j++){

                if(i!=j && search.equals(stdata[j])){
                    System.out.println("1");

                    end();
                }
        }
    }
        
        for(int i=0;i<N;i++){
            String[] splitdata = stdata[i].split("");
            
            
            if(!(splitdata[0].matches("H|D|C|S"))){
            //    System.out.println(splitdata[0]+" :2");
                // System.out.println(stdata[i]+" :2");

                end();
            }

            if(!splitdata[1].matches( "A|2|3|4|5|6|7|8|9|T|J|Q|K")){

                
                end();
            }
        }
        System.out.println("Yes"); 
}
    public static void end(){
        System.out.println("No");
        System.exit(0);
    }

}
