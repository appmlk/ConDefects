from sortedcontainers import SortedList,SortedSet,SortedDict

n,k = map(int,input().split())
p = list(map(int,input().split()))
s = SortedList()
idx = [0]*(n+1)
for i in range(n):
    idx[p[i]] = i
ans = float('inf')
for i in range(1,n+1):
    s.add(idx[i])
    if len(s) == k:
        ans = min(ans,s[-1]-s[0])
    if len(s) > k:
        s.discard(idx[i-k])
        ans = min(ans,s[-1]-s[0])
print(ans)