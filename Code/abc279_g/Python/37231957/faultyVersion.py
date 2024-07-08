n, k, c = list(map(int, input().split()))
MOD = 998244353

# DP[i] = A[i] 以降まで決まった数列で、最後の色の連続の左端indexが i であるものの個数
dp = [0]
# 0→i の遷移: iに限らず c*(c-1)  （特殊なのでDF配列では管理せず外側で持つ）
# (j=1～i-k+1)→i の遷移: DP[j]*(c-1)
# (j=i-k+2～i-1)→i の遷移: DP[j]*1
for i in range(1, n):
    s = dp[-1]
    t = dp[i - k + 1] if i - k + 1 > 0 else 0
    s -= t
    tmp = s + t * (c - 1) + c * (c - 1)
    dp.append((dp[-1] + tmp) % MOD)

ans = dp[-1] + c
print(ans)
