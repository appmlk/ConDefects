s=input()
n=len(s)
x=int(input())
q=[[0]*n for i in range(n)]
for i in range(1,n+1):
  for j in range(n):
    l=j
    r=j+i-1
    if not (0<=l<=r<n):
      break
    q[l][r]=r-l+1
    for k in range(l,r):
      q[l][r]=min(q[l][r],q[l][k]+q[k+1][r])
    if s[l]=="o":
      for k in range(l+1,r+1):
        if s[k]=="f" and q[l+1][k-1]==0:
          if k+x<r:
            q[l][r]=min(q[l][r],max(q[k+1][r]-x,0))
          else:
            q[l][r]=0
print(q[0][n-1])