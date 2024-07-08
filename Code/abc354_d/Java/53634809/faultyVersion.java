import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();
        long dx = C - A;
        long dy = D - B;
        long area = 0;

        //左下基準
        //y偶数のときx:2101のループ
        //y奇数のときx:1210のループ

        int[] even = {2,1,0,1}; //合計4
        int[] odd = {1,2,1,0};  //合計4

        

        //4*4エリアの面積*2は16
        //左下から4*4の枚数を数えて余った右辺の面積を求める
        
        area += (dx / 4) * 4 * dy; //左側4nまで面積
        


        //rightからDまで計算してない
        int right = (int) (C - dx % 4);

        //x座標
        for (int i = right; i < C; i++) {
            if((i + 1000000000) % 4 == 0 || (i + 1000000000) % 4 == 1){
                area += dy / 4 * 6;
            }else{
                area += dy / 4 * 2;
            }

            //y座標
            for(long j = (dy + 1000000000) % 4; j < D; j++){
                if(j % 2 == 0){
                    area += even[(i + 1000000000) % 4];
                }else{
                    area += odd[(i + 1000000000) % 4];
                }
                
            }
        }

        System.out.println(area);
    }    
}
