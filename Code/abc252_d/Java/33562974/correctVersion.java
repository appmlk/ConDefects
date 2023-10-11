class Main{
	public static void main(String[] args){
		java.util.Scanner sc = new java.util.Scanner(System.in);

		int N = sc.nextInt();

		int[] A = new int[N];
		for(int i=0;i<N;i++)A[i] = sc.nextInt();

		long[] sum = new long[(int)2e5+1];
		for(int i=0;i<N;i++)sum[A[i]]++;

		for(int i=1;i<sum.length;i++)sum[i] += sum[i-1];

		long count = 0;
		for(int i=0;i<N;i++) count += sum[A[i]-1]*(N-sum[A[i]]);

		System.out.println(count);
	}
}