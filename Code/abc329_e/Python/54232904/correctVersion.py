n,m=map(int,input().split())
s=input()
t=input()

dp=[[False]*(m+1) for i in range(n+1)]
dp[0][0]=True

for i in range(n):
    if dp[i][0]:
        for k in range(m):
            tl=t[k:]

            if i-k<0 or i+(m-k)>n:
                continue
            for j in range(1,len(tl)+1):
                if s[i:i+j]==tl[:j]:
                    dp[i+j][len(tl)-j]=True
    if i+m>n:
        continue
    for j in range(1,m+1):
        if dp[i][j]:
            for k in range(1,m+1):
                if s[i:i+k]==t[:k]:
                    dp[i+k][m-k]=True

print('Yes' if dp[-1][0] else 'No')