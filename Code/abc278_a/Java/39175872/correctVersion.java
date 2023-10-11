import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        int y = sc.nextInt();
//        int width = 0;
//
//        for (int i = 0; i < x ; i++){
//            int z = sc.nextInt();
//            if (z > y)
//                width+=2;
//            else
//                width+=1;
//        }
//        System.out.println(width);

        Queue <Integer> queue = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        for (int i = 0 ; i <x ; i++){
            int z = sc.nextInt();
            queue.add(z);
        }
        for (int i = 0 ; i < y ; i++){
            queue.remove();
            queue.add(0);
        }
       for (int i = 0 ; i < x; i++)
           System.out.print(queue.remove()+" ");

//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        int y = sc.nextInt();
//        int z = sc.nextInt();
//        if ()









    }
}