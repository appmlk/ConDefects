def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC258 E 1545 - Packing Potatoes PyPyで提出
# ベルトコンベアに載って 10^100 個のじゃがいもが 1 個ずつ流れてきます。
# 流れてくるじゃがいもの重さは長さ N の数列 W = (W0,…,W_{N-1}) で表され、i (1≤i≤10^100) 番目に流れてくるじゃがいもの重さは W_{(i-1) mod N} です。
# 高橋君は、まず空の箱を用意し、次のルールに従ってじゃがいもを順番に箱に詰めていきます。
# じゃがいもを箱に入れる。箱に入っているじゃがいもの重さの総和が X 以上になったら、その箱には蓋をし、新たに空の箱を用意する。
# Q 個のクエリが与えられます。
# i (1≤i≤Q) 番目のクエリでは、正整数 Ki が与えられるので、Ki 番目に蓋をされた箱に入っているじゃがいもの個数を求めてください。
# 問題の制約下で、蓋をされた箱が Ki 個以上存在することが証明できます。
# ・1 ≤ N, Q ≤ 2×10^5
# ・1 ≤ X ≤ 10^9 
# ・1 ≤ Wi ≤ 10^9 (0 ≤ i ≤ N-1)
# ・1 ≤ Ki ≤ 10^12 (1 ≤ i ≤Q)
# 周期性
N, Q, X = mp()
W = lmp()
M = 60
dp = [[0] * N for _ in range(M)]
# dp[k][i]: i の 2^k 個後の箱に何個のジャガイモが詰められるか
j = 0
tmp = 0
allsum = sum(W)
q = X // allsum
X %= allsum
for i in range(N):
    while tmp < X:
        tmp += W[j % N]
        j += 1
    dp[0][i] = j-i
    tmp -= W[i]
for k in range(M-1):
    for i in range(N):
        j = dp[k][i]
        dp[k+1][i] = j + dp[k][(i+j) % N]
for _ in range(Q):
    K = ip()
    i = 0
    for k in range(M)[::-1]:
        if K > (1<<k):
            i = (i + dp[k][i]) % N
            K -= 1<<k
    print(dp[0][i] + q*N)