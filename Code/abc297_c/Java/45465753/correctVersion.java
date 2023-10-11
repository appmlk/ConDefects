import java.util.*;
public class Main {
	public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      int h = sc.nextInt();
      int w = sc.nextInt();
      for(int i=0; i< h; i++){
        String[] target = sc.next().split("");
        List<Integer>list = new ArrayList<>();
        for(int j=0;j < w-1;j++){
          if(target[j].equals("T") && target[j+1].equals("T") && target[j].equals(target[j+1])){
            target[j] = "P";
            target[j+1] = "C";
            j++;
          }
        }
        System.out.println(String.join("", target));
      }
    }
}