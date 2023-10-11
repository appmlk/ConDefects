import javax.crypto.spec.PSource;
import java.util.*;

public class Main {
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        int ar[]=new int[n];
        for(int i=0;i<n;i++){
            ar[i]=scan.nextInt();
        }
        Arrays.sort(ar);

        for(int i=0;i<2000;i++){
            if(Arrays.binarySearch(ar,i)>=0)continue;
            else
                System.out.println(i);
            break;
        }

    }
}

