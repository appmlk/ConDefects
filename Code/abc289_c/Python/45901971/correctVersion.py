import itertools
n,m = map(int,input().split())
l = []
for i in range(m):
  c = int(input())
  a = list(map(int,input().split()))
  l.append(a)

ans = 0
for i in range(1,m+1):
  for j in itertools.combinations(l,i):
    s = set([])
    for k in j:
      s = s | set(k)
    if len(s) == n:
      ans += 1
print(ans)