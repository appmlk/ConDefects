
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Size: ");
        int n = sc.nextInt();
        int arr[] = new int[n];
        int max= 0;
        int b= 0;
        System.out.println("Array: ");
        for(int i=0 ; i< n ; i++)
        {
            arr[i] = sc.nextInt();
            if(max <= arr[i])
            {
                max = arr[i];
                b = i;
            }
            
        }

        if(max == arr[0] && b == 0)
        {
            System.out.println("0");
        }
        else
        {
            int result = max-(arr[0]-1);
            System.out.println(result);
        }
    }
}