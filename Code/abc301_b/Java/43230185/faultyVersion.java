import java.util.*;



public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n];

        for(int i = 0; i < n; i++){
            s[i] = sc.nextInt();
        }   

        System.out.print(s[0]+ " ");
        for(int i = 0; i < n - 1; i++){
            if(s[i] == s[i + 1] + 1 || s[i] == s[i + 1] - 1){
                System.out.print(s[i + 1]);
            }
            else{
                if(s[i] < s[i + 1]){
                    for(int j = s[i] + 1; j <= s[i + 1]; j++){
                        System.out.print(j + " ");
                    }  
                }
                else{
                    for(int j = s[i] - 1; j >= s[i + 1]; j--){
                        System.out.print(j + " ");
                    }  
                }
            }
        }
    }
}