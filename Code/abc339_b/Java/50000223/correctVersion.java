import java.util.Scanner;
public class Main {
    static int torus(int a, int max, int houkou){
        if(houkou>0 && 1<=a && a<max)a+=houkou;
        else if(houkou>0 && a==max)a=1;
        else if(houkou<0 && a>1 && a<=max)a+=houkou;
        else if(houkou<0 && a==1)a=max;
        return a;
    }

    public static void main(String[]srgs) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        String[][] masu = new String[h + 1][w + 1];
        //白にする
        for (int i = 0; i <= h; i++) for (int j = 0; j <= w; j++) masu[i][j] = ".";
        int tate = 1;
        int yoko = 1;
        String[] muki = {"", "ue", "migi", "sita", "hidari"};
        int mukiNo = 1;

        for (int i = 1; i <= n; i++) {
            //System.out.println("(" + tate + ", " + yoko + ")から始まる");
            if (masu[tate][yoko].equals(".")) {
                //System.out.println("白だ！");
                masu[tate][yoko] = "#";
                //System.out.println(torus(mukiNo, 4, 1));
                mukiNo = torus(mukiNo, 4, 1);

                //System.out.println(muki[mukiNo]+"に進む");
                if (muki[mukiNo] == "ue") {
                    tate = torus(tate, h, -1);
                } else if (muki[mukiNo].equals("migi")) {
                    yoko = torus(yoko, w, 1);
                } else if (muki[mukiNo].equals("sita")) {
                    tate = torus(tate, h, 1);
                } else if (muki[mukiNo].equals("hidari")) {
                    yoko = torus(yoko, w, -1);
                }

            } else if (masu[tate][yoko].equals("#")) {
                //System.out.println("黒だ！");
                masu[tate][yoko] = ".";
                mukiNo = torus(mukiNo, 4, -1);
                if (muki[mukiNo] == "ue") {
                    tate = torus(tate, h, -1);
                } else if (muki[mukiNo] == "migi") {
                    yoko = torus(yoko, w, 1);
                } else if (muki[mukiNo] == "sita") {
                    tate = torus(tate, h, 1);
                } else if (muki[mukiNo] == "hidari") {
                    yoko = torus(yoko, w, -1);
                }
            }
        }
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                
                System.out.print(masu[i][j]);
            }
            System.out.println();
        }
    }
}
