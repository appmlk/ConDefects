n = int(input())
l,r = zip(*[list(map(int,input().split())) for i in range(n)])
l = list(l)
r = list(r)
l.sort(reverse=True)
r.sort()
ans = 0
for i in range(n):
  ans += max(0,l[i]-r[i])*(n-2*i-1)
print(ans)