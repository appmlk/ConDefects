import java.util.*;
public class Main {
    static List<Integer> ar=new ArrayList<Integer>();
    public static void main (String[]args){
     Scanner scan=new Scanner(System.in);
     int N=scan.nextInt();
     HashMap<Integer, Character>hashMap=new HashMap<Integer, Character>();
     ArrayList<Character> l=new ArrayList<>();
     char c;
     for(c='a';c<='z';++c){
         l.add(c);
     }
     for(int i=97,z=0; i<122;z++,i++){
         hashMap.put(i,l.get(z));
     }
        System.out.println(hashMap.get(N));
    }

}
