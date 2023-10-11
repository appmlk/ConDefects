import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long L = sc.nextLong();
		long W = sc.nextLong();
		long now = 0;
		long answer = 0;
		while(N-->0){
			long next = sc.nextLong();
			if(next<=now){
				now = next+W;
				continue;
			}
			long sub = next-now;
			answer += sub/W;
			answer += (sub%W)>0 ? 1 : 0;
			now = next+W;
		}
		answer += (L-now)/W;
		answer += (L-now)*((L-now)%W)>0 ? 1 : 0;
		System.out.println(answer);
	}
}