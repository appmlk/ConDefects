import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        String sTails[] = new String[n];

        for(int i=0; i<n; i++){
            String tmp = sc.next();
            sTails[i] = tmp.substring(3, 6);

        }

        int count = 0;

        String[] tList = new String[m];

        for(int i=0; i<m; i++){
            tList[i] = sc.next();
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(sTails[i].equals(tList[j])){
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);


   
    }
}