import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int rate = 0;
		switch(s) {
		case "tourist":
			rate = 3858;
			break;
		case "ksun48":
			rate = 3679;
			break;
		case "Benq":
			rate = 3658;
			break;
		case "Um_nik":
			rate = 3648;
			break;
		case "apiad":
			rate = 3838;
			break;
		case "Stonefeang":
			rate = 3630;
			break;
		case "ecnerwala":
			rate = 3613;
			break;
		case "mnbvmar":
			rate =3555;
			break;
		case "newbiedmy":
			rate = 3516;
			break;
		case "semiexp":
			rate =  3481;
			break;
		}
		
		System.out.println(rate);
	}
	
	
}