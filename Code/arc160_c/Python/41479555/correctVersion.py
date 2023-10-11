N = int(input())
A = list(map(lambda x:int(x)-1,input().split()))
mod = 998244353
mv = 2*10**5+65
count = [0]*(mv)
for i in A:
    count[i] += 1
ans = 0
dp = dict()
dp[0] = 1
for i in range(mv):
    change = dict()
    value = []
    for j in dp.keys():
        cur = (count[i] + j) // 2
        if cur not in change.keys():
            change[cur] = dp[j]
            value.append(cur)
        else:
            change[cur] += dp[j]
            change[cur] %= mod
    nextdp = dict()
    maxv = max(value)
    now = 0
    for j in range(maxv,-1,-1):
        if j in change.keys():
            now += change[j]
            now %= mod
        nextdp[j] = now
    dp = nextdp
print(dp[0])