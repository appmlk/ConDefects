# 長さnのリストRと長さnのリストCが与えられます。
# また、q個のクエリが与えられます。
# 各クエリは、2つの整数aとbが与えられ、Rのa番目の要素とCのb番目の要素を足した値がnより大きい場合は'#'を、そうでない場合は'.'を出力してください。
# という問題と同義。

def solve(n,R,C,Q,queries):
    ans = []
    for r,c in queries:
        if R[r-1]+C[c-1] > n:
            ans.append("#")
        else:
            ans.append(".")
    return ans

if __name__=="__main__":
    n = int(input())
    R = list(map(int,input().split()))
    C = list(map(int,input().split()))
    Q = int(input())
    queries = [list(map(int,input().split())) for _ in range(Q)]
    ans = solve(n,R,C,Q,queries)
    print(*ans)