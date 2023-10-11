n=int(input())
r=list(map(int,input().split()))
c=list(map(int,input().split()))
q=int(input())
ans=""
for i in range(q):
  y,x=map(int,input().split())
  ans+="#" if r[y-1]+c[y-1]>n else "."
print (ans)