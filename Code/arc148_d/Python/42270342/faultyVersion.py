from collections import defaultdict
n,m = map(int,input().split())
a = list(map(int,input().split()))
a.sort()
d = defaultdict(int)
for i in a:
  d[i] += 1
  d[i] %= 2
flag = True
idx = []
for k,v in d.items():
  flag &= not v
  if k <= m//2:
    idx.append(k)
if flag:
  print("Bob")
elif m%2:
  print("Alice")
else:
  flag = True
  cnt = 0
  for i in idx:
    if (d[i] and d[i+m//2]):
      cnt += 1
    flag &= (d[i] and d[i+m//2]) or (not d[i] and not d[i+m//2])
  if flag and not cnt%2:
    print("Bob")
  else:
    print("Alice")