import java.util.*;
import java.math.*;

class Main{
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
      	String todayNumberStr = sc.next();		// こいつはそのまま先頭に付けるので文字列
      	long tomorrowNumber = Long.parseLong(sc.next() + "0");	// 2で割るので余り防止
      	tomorrowNumber /= 2;
      	String superLucky = todayNumberStr + "0" + String.valueOf(tomorrowNumber);	// 0をねじ込み桁上がり阻止
      	System.out.println(superLucky);
    }
}