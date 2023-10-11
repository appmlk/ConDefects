N = int(input())
S = input()
mod = 998244353

DP = [[[0]*26 for _ in range(26)] for __ in range(N)]
DP2 = [[0]*26 for __ in range(N)]

def f(s):
    return ord(s) - ord("a")


if S[0]=="?" and S[1]=="?":
    DP[1] = [[1]*26 for _ in range(26)]
    for i in range(26):
        DP[1][i][i] = 0
elif S[0]=="?" and S[1]!="?":
    s = f(S[1])
    DP[1][s] = [1]*26
    DP[1][s][s] = 0
elif S[0]!="?" and S[1]=="?":
    s = f(S[0])
    for i in range(26):
        DP[1][i][s] = 1
    DP[1][s][s] = 0
else:
    i, j = f(S[0]), f(S[1])
    DP[1][i][j] = 1 if i != j else 0


for n in range(2, N):
    if S[n] == "?":        
        for i in range(26):
            for j in range(26):
                if i == j: continue
                for k in range(26):
                    if i==k or j==k: continue
                    DP[n][i][j] += DP[n-1][j][k]
                    DP[n][i][j] %= mod
            DP2[n][i] = sum(DP[n][i]) % mod
    else:
        i = f(S[n]) 
        for j in range(26):
            if i == j: continue
            for k in range(26):
                if i==k or j==k: continue
                DP[n][i][j] += DP[n-1][j][k]
                DP[n][i][j] %= mod
        DP2[n][i] = sum(DP[n][i]) % mod

print(sum(DP2[-1])%mod, sep="\n")
