// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int M = input.nextInt();
		input.nextLine();
		String S = input.nextLine();
		String T = input.nextLine();
		if (N == M && S.equals(T))
		{
			System.out.println("0");
		}else if (N == M && !(S.equals(T)))
		{
			System.out.println("3");
		}else {
				String front = "";
		String back = "";
		for(int i = 0; i < N; i++)
		{
			front += T.charAt(i);
		}
		for(int j = M - N; j < M; j++)
		{
			back += T.charAt(j);
		}
		if (S.equals(front) && S.equals(back))
		{
			System.out.println("0");
		} else if (S.equals(front)){
			System.out.println("1");
		}else if (S.equals(back))
		{
			System.out.println("2");
		}else
		{
			System.out.println("3");
		}

		}
		
		
	}

}