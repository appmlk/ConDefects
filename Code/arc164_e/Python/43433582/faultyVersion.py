import sys
readline = sys.stdin.readline

#n = int(readline())
#*a, = map(int,readline().split())
# b = [list(map(int,readline().split())) for _ in range()]

n,Q = map(int,readline().split())
LR = []
s = {0,n}
for _ in range(Q):
    a,b = map(int,readline().split())
    a -= 1
    LR.append((a,b))
    s.add(a)
    s.add(b)

s = sorted(s)
x = len(s)-1
d = 0
v = 1
while v < x:
    v *= 2
    d += 1

C = x - 2**(d-1)
if x == 1:
    print(Q)
    exit()
"""
x 個の区間の中から C ペア選んで、それぞれに含まれる区間の個数の最小値
"""
imos = [0]*(n+1)
"""
for L,R in LR:
    imos[L] += 1
    imos[R] -= 1
for i in range(1,n):
    imos[i] += imos[i-1]
"""
for L,R in LR:
    imos[L] += 1
    imos[R] += 1

#print(LR)
#print(s)
#print(imos)
#print(x,C,[imos[i] for i in s])

INF = 1<<60
dp = [[INF]*(C+1) for _ in range(x+1)]
dp[0][0] = 0
for i in range(x):
    for j in range(C+1):
        if i+2 <= x and j+1 <= C: dp[i+2][j+1] = min(dp[i+2][j+1], dp[i][j] + imos[s[i+1]])
        dp[i+1][j] = min(dp[i+1][j],dp[i][j])

#for i in dp:
#    print(i)

print(d,dp[x][C]*2)




















