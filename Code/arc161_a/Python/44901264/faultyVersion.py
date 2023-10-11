n=int(input())
a=list(map(int,input().split()))
a.sort()
if(n>=2):
  a=a[1:]
cnt=0
ans='Yes'
for i in range(max(1,n-1)):
  if(i>=1 and a[i]==a[i-1]):
    cnt+=1
  else:
    cnt=1
  if(cnt>=(n+1)//2):
    ans='No'
    break
print(ans)