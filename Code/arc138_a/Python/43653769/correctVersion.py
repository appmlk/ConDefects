n,k=map(int,input().split())
a=list(map(int,input().split()))
st=set()
t=[]
if min(a[:k])>=max(a[k:]):
  print(-1)
  exit()
for i in range(k+1,n):a[i]=max(a[i],a[i-1])
for i in range(k,n):
  if a[i]in st:continue
  st.add(a[i])
  t.append((a[i],i))
t.sort()
import bisect
ans=10**20
# print(t)
for i in range(k):
  p=bisect.bisect_right(t,(a[i],10**10))
  # print(p)
  try:ans=min(ans,t[p][1]-i)
  except:pass
print(ans)