n=int(input())
S=input()
dp=["2"]*(n+1)
Q=[[] for _ in range(n)]
for l in range(n):
  i=0
  for r in range(l,min(l+200,n)):
    s=S[l:r+1]
    while dp[i]<s:
      i+=1
    Q[r].append((i,s))
  for i,s in Q[l]:
    dp[i]=min(dp[i],s)
  Q[l].clear()
print(dp.index("2"))