N = int(input())
A = [int(input()) for i in range(N)]

MOD = [998244353, 1000000007, 1000000009, 1000000021]
M = len(MOD)

C = [[A[i] % MOD[c] for i in range(N)] for c in range(M)]
d = [{} for _ in range(M)]
for c in range(M):
    for i in range(N):
        if C[c][i] not in d[c]:
            d[c][C[c][i]] = 1
        else:
            d[c][C[c][i]] += 1


ans = 0
for i in range(N):
    for j in range(N):
        f = True
        for c in range(M):
            f &= ((C[c][i] * C[c][j]) % MOD[c]) in d[c]
        if f : 
            ans += d[c][(C[c][i] * C[c][j]) % MOD[c]]
print(ans)