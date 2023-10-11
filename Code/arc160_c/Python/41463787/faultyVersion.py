MOD = 998244353 
M = 2*10**5+2
N = int(input())
A = list(map(int,input().split()))
B = [0]*M

for a in A:
    B[a] += 1

dp = [1]
for i in range(1,M):
    b = B[i]
    S = [0]*((len(dp)-1+b)//2+2)
    for i in range(len(dp)):
        S[0] += dp[i]
        S[(b+i)//2+1] += -dp[i]
    
    #print(S)
    
    S[0] %= MOD
    for i in range(len(S)-1):
        S[i+1] += S[i]
        S[i+1] %= MOD

    dp = S[:-1]
    #print(b,dp)

print(sum(dp)%MOD)