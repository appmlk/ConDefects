# coding: utf-8
# Your code here!
n,m=map(int,input().split())
s=input()
t=input()
dp=[[[False]for _ in range(m+1)]for _ in range(n+1)]
dp[0][0]=True
for i in range(n):
    if i+m<=n:
        for j in range(m+1):
            if dp[i][j]:
                dp[i][0]=True
    if dp[i][m]:
        for j in range(m+1):
            dp[i][j]=True
    for j in range(m):
        if dp[i][j] and s[i]==t[j]:
            dp[i+1][j+1]=True
print("Yes" if dp[n][m] else "No")