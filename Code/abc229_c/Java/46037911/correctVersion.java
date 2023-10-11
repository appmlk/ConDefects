import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class tts{
    public int a;
    public int b;
    tts(int aa, int bb){
        a=aa;
        b=bb;
    }
    public String toString(){
        return a+" "+b+" ";
    }
}

public class Main {
    public static void main(String[] args) {
          Scanner sc =new Scanner(System.in);
          int a = sc.nextInt();
          int m = sc.nextInt();
          int v,b;

          long r=0;
          ArrayList<tts> list = new ArrayList<>();
          
          for(int i =0;i<a;i++){
                v=sc.nextInt();
                b=sc.nextInt();
                tts ass= new tts(v,b);
                list.add(ass );
          }
          Collections.sort(list,(p1,p2)->p1.a-p2.a);
          for (tts tts : list) {
            //    System.out.println(tts.toString());
               
          }
          for (int i = a-1; i >= 0; i--) {
            //   System.out.println(i);
            //   System.out.println(m);
              if(m==0)break;
            //   System.out.println(r);
              r+=((long)list.get(i).a)*(Math.min(list.get(i).b,m));
              m-=Math.min(list.get(i).b,m);
          }
          System.out.println(r);}
}          