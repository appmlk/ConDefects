import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] array = new int[size];

        for(int i=0; i<size; i++)
        {
            array[i] = scanner.nextInt();
        }
        int alternate = 0;
        int num_position = 0;
        long res = 0;
        for(int i=0; i<size; i++)
        {
            if(array[i]==i+1)
            {
                num_position += 1;
            }
            else
            {
                if(array[array[i]-1] == i+1)
                {
                    alternate += 1;
                }
            }
        }
        res = (alternate/2) + (1L)*(num_position-1)*(num_position)/2;
        System.out.println(res);
    }
}
