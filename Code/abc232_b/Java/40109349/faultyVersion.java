import java.util.Scanner;

class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String T = sc.next();
        int SK[] = new int[S.length()];
        int TK[] = new int[T.length()];
        String obj = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        int diff = 0;
        for(int i = 0; i < S.length(); i++){
            for(int j = 0; j < 26; j++){
                if(S.charAt(i) == obj.charAt(j)){
                    SK[i] = j;
                    break;
                }
            }
            for(int j = SK[0]; j < SK[0] + 26; j++){
                if(T.charAt(i) == obj.charAt(j)){
                    TK[i] = j;
                    break;
                }
            }
        }

        // for(int i = 0; i < S.length(); i++){
        //     System.out.print(SK[i] + " ");
        // }
        // System.out.println();
        // for(int i = 0; i < S.length(); i++){
        //     System.out.print(TK[i] + " ");
        // }
        // System.out.println();

        diff = TK[0] - SK[0];
        boolean flag = true;
        for(int i = 0; i < T.length(); i++){
            if(diff != TK[i] - SK[i]){
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
        sc.close();
        return;
    }
}