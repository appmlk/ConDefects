import java.util.*;



public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s[] = sc.next().split("");
        int count = 0;

        for(int i = 0; i < n; i++){
            if(s[i].equals("o")){
                count += 1;
                if(count > k){
                    s[i] = "x";
                }
            }
            System.out.print(s[i]);
        }
    }
}