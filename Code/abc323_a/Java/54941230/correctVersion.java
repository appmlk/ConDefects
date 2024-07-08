import java.util.*;

public class Main{
public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  
  String st = sc.nextLine();
  long s = Long.parseLong(st); //16桁：Long 
  //intは32bit　大体10桁(2^32が42億くらい　
  //負の数で半分持ってかれて大体-21億~21億)
  boolean isZ = true;
  
  for(int i = 1; i<17; i++){ //0番目~15番目
    if(i%2 == 1){
      if(s%10 != 0){
        isZ = false;
        System.out.println("No");
        return;
      }
    }
    s = s/10;
  }
  if(isZ){
  System.out.println("Yes");
  }
  
  
}

}
