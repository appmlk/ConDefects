import java.util.*;
class Main {
 	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
//-------入力の読み取り-----
        int n = sc.nextInt();
        
//-----Scannerを閉じる------
        sc.close();

//----------処理----------
        boolean f = true;
        int a = -1;
        int b = 0;
        while(n > 0){
            b = n % 10;
            if(b <= a){
                f = false;
            }
            a = b;
            n /= 10;
            }
//----------出力----------
        System.out.println(yorn(f));
        }

//----------------------以下メソッド--------------------------
 
    public static String yorn (boolean flag){
     //trueならYes、falseならNoを返す
    String answer;
      if(flag){
          answer = "Yes";
      }else{
          answer = "No";
      }
        return answer;
    }


}
    
        