import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 標準入力受取内容を書く
        int N = sc.nextInt();
        int M = sc.nextInt();
        int P = sc.nextInt();
        int c = 0;
        for(int i = 0; i <= N; i++){
            if(N >= M+P*i){
                c += 1;
            }else{
                System.out.println(c);
                break;
            }
        }
        sc.close();
        return; //「おわり」の呪文
    }
}
