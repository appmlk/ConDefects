def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC253 1073 E - Distance Sequence PyPyで提出
# 長さ N の整数からなる数列 A = (A1,…,AN) であって、以下の条件を全て満たすものは何通りありますか？
# ・1 ≤ Ai ≤ M (1 ≤ i ≤ N)
# ・|Ai - A_{i+1}| ≥ K (1 ≤ i ≤ N-1)
# ただし、答えは非常に大きくなることがあるので、答えを 998244353 で割った余りを求めてください。
# ・2 ≤ N ≤ 1000
# ・1 ≤ M ≤ 5000
N, M, K = mp()
MOD = 998244353
dp = [1] * (M+1)
dp[0] = 0
# dp[i]: 最後がiとなる数列の場合の数
for _ in range(N-1):
    # print(f'dp = {dp}')
    cum = [0] * (M+1)
    for j in range(M):
        cum[j+1] = cum[j] + dp[j+1]
    # print(f'cu = {cum}')
    ndp = [0] * (M+1)
    for j in range(1, M+1):
        if j+K <= M:
            ndp[j] += cum[M] - cum[j+K-1]
        if j-K >= 1:
            ndp[j] += cum[j-K]
        ndp[j] %= MOD
    dp = ndp
    # print(f'dp = {dp}')
print(sum(dp) % MOD)