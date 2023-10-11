import java.util.*;

public class Main
{
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        // 数
        long n = sc.nextLong();

        // a~d
        long a = 0;
        long b = 0;
        long c = 0;
        long d = 0;

        // カウント
        long count = 0;

        // テスト用　結果をすべて格納する
        // ArrayList<String>  test = new ArrayList<>(); 

        // a*aがnの半分未満の間、aを総当たりする
        for (a=1; a*a < n/2; a++)
        {
            // bはa以上かつabがnの半分未満
            for (b=a; a*b <= n/2; b++)
            {
                // cはc*cがn-ab未満
                for (c=1; c*c <= n-a*b; c++)
                {
                    // System.out.println(a+" "+b+" "+c);
                    // 条件を満たすdが存在するか
                    if ((n-a*b)%c == 0)
                    {
                        // 存在した場合
                        d = (n-a*b)/c;

                        // 並び替えのパターン
                        long inc = 1;
                        // boolean c1=false,c2=false,c3=false; //test

                        if (a != b)
                        {
                            inc*= 2;
                            // c1 = true; // test
                        }

                        if (c != d)
                        {
                            inc*= 2;
                            // c2 = true; // test
                        }

                        if (a != c || b != d)
                        {
                            inc*= 2;
                            // c3 = true; // test
                        }

                        // test
                        // if (c1&&c2&&c3)
                        // {
                        //     if (test.contains(d+" "+c+" "+b+" "+a))
                        //     {
                        //         System.out.println(a+" "+b+" "+c+" "+d);
                        //     }
                        //     else
                        //     {
                        //         test.add(d+" "+c+" "+b+" "+a);
                        //     }
                        // }
                        // if (c1&&c2)
                        // {
                        //     if (test.contains(b+" "+a+" "+d+" "+c))
                        //     {
                        //         System.out.println(a+" "+b+" "+c+" "+d);
                        //     }
                        //     else
                        //     {
                        //         test.add(b+" "+a+" "+d+" "+c);
                        //     }
                        // }
                        // if (c1&&c3)
                        // {
                        //     if (test.contains(c+" "+d+" "+b+" "+a))
                        //     {
                        //         System.out.println(a+" "+b+" "+c+" "+d);
                        //     }
                        //     else
                        //     {
                        //         test.add(c+" "+d+" "+b+" "+a);
                        //     }
                        // }
                        // if (c1)
                        // {
                        //     if (test.contains(b+" "+a+" "+c+" "+d))
                        //     {
                        //         System.out.println(a+" "+b+" "+c+" "+d);
                        //     }
                        //     else
                        //     {
                        //         test.add(b+" "+a+" "+c+" "+d);
                        //     }
                        // }
                        // if (c2&&c3)
                        // {
                        //     if (test.contains(d+" "+c+" "+a+" "+b))
                        //     {
                        //         System.out.println(a+" "+b+" "+c+" "+d);
                        //     }
                        //     else
                        //     {
                        //         test.add(d+" "+c+" "+a+" "+b);
                        //     }
                        // }
                        // if (c2)
                        // {
                        //     if (test.contains(a+" "+b+" "+d+" "+c))
                        //     {
                        //         System.out.println(a+" "+b+" "+c+" "+d);
                        //     }
                        //     else
                        //     {
                        //         test.add(a+" "+b+" "+d+" "+c);
                        //     }
                        // }
                        // if (c3)
                        // {
                        //     if (test.contains(c+" "+d+" "+a+" "+b))
                        //     {
                        //         System.out.println(a+" "+b+" "+c+" "+d);
                        //     }
                        //     else
                        //     {
                        //         test.add(c+" "+d+" "+a+" "+b);
                        //     }
                        // }
                        // if (test.contains(a+" "+b+" "+c+" "+d))
                        // {
                        //     System.out.println(a+" "+b+" "+c+" "+d);
                        // }
                        // else
                        // {
                        //     test.add(a+" "+b+" "+c+" "+d);
                        // }

                        if (!(a*b == c*d && a < c))
                        {
                            // カウントを増やす
                            count += inc;
                        }

                        // System.out.println(inc+"x"+a+" "+b+" "+c+" "+d);
                    }
                }
            }
        }

        // 出力
        System.out.println(count);
        // System.out.println(test.size());
        // for (String s:test)
        // {
        //     System.out.println(s);
        // }
    }
}
