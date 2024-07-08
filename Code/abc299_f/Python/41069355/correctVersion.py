S = input()
S = [ord(s)-ord('a') for s in S]
inf = 1<<30
MOD = 998244353

ans = 0
for i in range(1,len(S)):
    s = S[:i]
    t = S[i:]

    ls = len(s)
    lt = len(t)

    ns = [[inf]*26 for _ in range(ls+1)]
    nt = [[inf]*26 for _ in range(lt+1)]

    for j in range(ls-1,-1,-1):
        for k in range(26):
            if s[j] == k:
                ns[j][k] = j
            else:
                ns[j][k] = ns[j+1][k]
    
    for j in range(lt-1,-1,-1):
        for k in range(26):
            if t[j] == k:
                nt[j][k] = j
            else:
                nt[j][k] = nt[j+1][k]

    dp = [[0]*(lt) for _ in range(ls)]
    
    if ns[0][t[0]] != inf:
        dp[ns[0][t[0]]][0] = 1
    else:
        continue

    for j in range(ls):
        for k in range(lt):
            if ns[j+1][t[0]] == inf:
                ans += dp[j][k]
                ans %= MOD
            for m in range(26):
                if ns[j+1][m] != inf and nt[k+1][m] != inf:
                    dp[ns[j+1][m]][nt[k+1][m]] += dp[j][k]
                    dp[ns[j+1][m]][nt[k+1][m]] %= MOD

print(ans)