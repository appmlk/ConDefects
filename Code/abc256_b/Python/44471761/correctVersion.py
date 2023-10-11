n=int(input())
a=list(map(int,input().split()))

for i in range(n):
  for j in range(i):
    a[j]+=a[i]
ans=0
for i in range(n):
  ans+= a[i]>=4
print(ans)