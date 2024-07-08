# i番目の重みws[i], 価値vs[i]
def solve(N, W, ws, vs):
    dp = [0] * (W+1)
    for i in range(N):
        # 価値v, 重さw
        v = vs[i]; w = ws[i]
        for j in range(w, W+1):
            dp[j] = max(dp[j-w] + v, dp[j])
    return max(dp)

N = int(input())
A = list(map(int,input().split()))
# 初日と翌週初日はお休み
# 開けた日数分の労働力を前計算
# あとはナップサック
from itertools import accumulate
R = list(accumulate(A))
V = [R[0]] + [R[i//2] + R[(i+1)//2] for i in range(N)]
W = [i+2 for i in range(N)]
# print(V,W)
ans = solve(N,N,W,V)
print(ans)