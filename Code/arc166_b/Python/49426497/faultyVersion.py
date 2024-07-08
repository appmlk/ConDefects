n,a,b,c = map(int, input().split())
al = list(map(int, input().split()))

def gcm(a,b):
    if b == 0:
        return a
    else:
        return gcm(b,a%b)
    
def calc(x,y):
    if x%y == 0:
        return x
    else:
        return y * (x//y + 1)

ab_gcm = gcm(a,b)
ab = a*b//ab_gcm
ac_gcm = gcm(a,c)
ac = a*c//ac_gcm
bc_gcm = gcm(b,c)
bc = b*c//bc_gcm
abc_gcm = gcm(ab,c)
abc = ab*c//abc_gcm

dl = [[0] * 8 for i in range(n)]
for i,l in enumerate(al):
    dl[i][int('001',2)] = calc(l,a) -  l
    dl[i][int('010',2)] = calc(l,b) -  l
    dl[i][int('100',2)] = calc(l,c) -  l
    dl[i][int('011',2)] = calc(l,ab) -  l
    dl[i][int('101',2)] = calc(l,ac) -  l
    dl[i][int('110',2)] = calc(l,bc) -  l
    dl[i][int('111',2)] = calc(l,abc) -  l

dp=[[1000000000000000000] * 9 for i in range(n+1)]

for i,d in enumerate(dl):
    dp[i+1][int('001',2)] = min(dp[i][int('001',2)],d[int('001',2)])
    dp[i+1][int('010',2)] = min(dp[i][int('010',2)],d[int('010',2)])
    dp[i+1][int('100',2)] = min(dp[i][int('100',2)],d[int('100',2)])
    dp[i+1][int('011',2)] = min(dp[i][int('011',2)],d[int('011',2)],dp[i][int('001',2)]+d[int('010',2)],dp[i][int('010',2)]+d[int('001',2)])
    dp[i+1][int('101',2)] = min(dp[i][int('101',2)],d[int('101',2)],dp[i][int('001',2)]+d[int('100',2)],dp[i][int('100',2)]+d[int('001',2)])
    dp[i+1][int('011',2)] = min(dp[i][int('110',2)],d[int('110',2)],dp[i][int('010',2)]+d[int('100',2)],dp[i][int('100',2)]+d[int('010',2)])
    dp[i+1][int('111',2)] = min(dp[i][int('111',2)], d[int('111',2)]\
                                ,dp[i][int('011',2)]+d[int('100',2)],dp[i][int('101',2)]+d[int('010',2)],dp[i][int('110',2)]+d[int('001',2)]\
                                ,dp[i][int('001',2)]+d[int('110',2)],dp[i][int('010',2)]+d[int('101',2)],dp[i][int('100',2)]+d[int('011',2)])

print(dp[n][7])