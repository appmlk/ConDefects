import java.util.*;
 
class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int X = sc.nextInt();
      int Y = sc.nextInt();
      int Z = sc.nextInt();
      if((0 < Y && Y < X && Y < Z) || (Y < 0 && X < Y && Z < Y)){
        System.out.println("-1");
        return;
      }
      if((X > Y && Y > 0 && 0 > Z) || (X < Y && Y < 0 && 0 < Z))System.out.println(Math.abs(X) + Math.abs(2 * Z));
      else System.out.println(Math.abs(X));
    }
}