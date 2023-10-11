n=int(input())
a=list(map(int,input().split()))
l=0
while l<n:
  if a[l]^(l&1):
    l+=1
  else:
    break
r=0
a+=[0]
for i in range(l,n):
  r+=a[i]^a[i+1]
print ("Yes" if l>=r else "No")