import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Double h = Double.parseDouble(br.readLine());
		
		h *= (12800000 + h);
		System.out.println(Math.sqrt(h));
	}
}