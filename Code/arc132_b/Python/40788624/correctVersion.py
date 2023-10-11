n=int(input())
p=list(map(int,input().split()))

if p[0]==1 and p[1]==2:
  ans=0
elif p[0]<p[1]:
  ans=min(n-p[0]+1,2+p[0]-1)
else:
  ans=min(p[0]+1,n-p[0]+1)
print(ans)