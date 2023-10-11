
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		String s;
		char[][] field = new char[9][9];
		ArrayList<Integer> koma = new ArrayList<>();
		int ans = 0;
		int[] c = new int[4];
		for(int i = 0; i< 9;i++) {
			s =sc.next();
			for(int j = 0;j < 9;j++) {
				field[i][j] = s.charAt(j);
				if(s.charAt(j) == '#') {
					koma.add(i * 9 + j);
				}
			}
		}
	//System.out.print(koma);
		for(int i = 0;i < koma.size();i++) {
			for(int j = i + 1;j < koma.size();j++) {
				for(int k = j + 1;k < koma.size();k++) {
					for(int l = k + 1 ;l < koma.size();l++) {
						c[0] = koma.get(i);
						c[1] = koma.get(j);
						c[2] = koma.get(k);
						c[3] = koma.get(l);
						if((c[1]/9 - c[0]/9) * (c[1]/9 - c[0]/9) + (c[1]%9 - c[0]%9) * (c[1]%9 - c[0]%9)
								== (c[0]/9 - c[2]/9) * (c[0]/9 - c[2]/9) + (c[0]%9 - c[2]%9) * (c[0]%9 - c[2]%9)
								&& (c[3]/9 - c[2]/9) * (c[3]/9 - c[2]/9) + (c[3]%9 - c[2]%9) * (c[3]%9 - c[2]%9)
								== (c[1]/9 - c[3]/9) * (c[1]/9 - c[3]/9) + (c[1]%9 - c[3]%9) * (c[1]%9 - c[3]%9)
								&& (c[1]/9 - c[3]/9) * (c[1]/9 - c[3]/9) + (c[3]%9 - c[1]%9) * (c[3]%9 - c[1]%9)
								== (c[1]/9 - c[0]/9) * (c[1]/9 - c[0]/9) + (c[1]%9 - c[0]%9) * (c[1]%9 - c[0]%9)
								&& (c[3]/9 - c[0]/9) * (c[3]/9 - c[0]/9) + (c[3]%9 - c[0]%9) * (c[3]%9 - c[0]%9)
								== (c[2]/9 - c[1]/9) * (c[2]/9 - c[1]/9) + (c[2]%9 - c[1]%9) * (c[2]%9 - c[1]%9)) {
							//System.out.println(c[0]+ " " + c[1]+ " " + c[2] +" " +c[3]);
						//	System.out.println(" " +ans);
							ans++;
						}
					}
				}
			}
		}
		System.out.print(ans);
	}

}