import sys
readline = sys.stdin.readline

n = int(readline())
*a, = map(int,readline().split())
a.append(a[-1])

def solve(a):
    # 隣接する同じ元がない
    n = len(a)
    if n <= 2: return 1
    # ababa パターンはダメ、それ以外はok
    same = list(range(n))
    for i in range(2,n):
        if a[i] == a[i-2]:
            same[i] = same[i-2]

    #print(a)
    #print(same)

    dp = [0]*n
    acc = [0]*n
    dp[0] = acc[0] = dp[1] = 1
    acc[1] = 2
    for i in range(2,n):
        r = 0
        p = same[i]
        q = same[i-1]
        if q <= p-1:
            r = p-1
        elif q == p+1:
            r = p
        else:
            r = q-1
        if r > i-2: r = i
        dp[i] = acc[r-1] if r else 0
        if r <= i-1:
            dp[i] += dp[i-1]
        if r <= i-2:
            dp[i] += dp[i-2]
        dp[i] %= MOD

        acc[i] = (acc[i-1] + dp[i])%MOD
    #print(dp)
    #print(acc)
    #print()
    return dp[-1]



MOD = 998244353
ans = 1
r = []
for ai in a:
    if not r or r[-1] != ai:
        r.append(ai)
    else:
        ans = ans*solve(r)%MOD
        r = [ai]
print(ans)


