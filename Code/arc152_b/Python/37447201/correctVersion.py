import bisect
n,l = map(int,input().split())
a = list(map(int,input().split()))
mi = l
for i in range(n):
  aa = bisect.bisect_right(a,l-a[i])
  if aa == n:
    m = abs(a[i] - (l-a[aa-1]))
  else:
    m = min(abs(a[i] - (l-a[aa-1])),abs(a[i] - (l-a[aa])))
  mi = min(mi,m)
print(2 * (l + mi))