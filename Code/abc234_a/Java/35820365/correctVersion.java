import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		long t = scan.nextLong();



		if(t == 0) {
			long ft = 3;

			long ftt = ((ft * ft) + (ft * 2) + 3) + ((ft * ft) + (ft * 2) + 3);

			long fttt = (ftt * ftt) + (2 * ftt) + 3;;
			
			System.out.println(fttt);
		} else {
			long ft = (t * t) + (2 * t) + 3;

			long ftt = ((ft * ft) + (2 * ft) + 3) + ((ft + t)*(ft + t) + (2*(ft + t)) + 3);

			long fttt = (ftt * ftt) + (2 * ftt) + 3;

			System.out.println(fttt);

		}


	}

}
