import java.util.*;



public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s[] = new String[n];
        
        for(int i = 0; i < n; i++){
            s[i] = sc.next();
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j){
                    boolean flag = true;
                    String[] strs = (s[i] + s[j]).split("");
                    for(int k = 0; k <= strs.length / 2; k++){
                        if(!(strs[k].equals(strs[strs.length -1 - k]))){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }
        System.out.println("No");
    }
}