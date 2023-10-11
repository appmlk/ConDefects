from collections import defaultdict
n = int(input())
a = list(map(int,input().split()))
mae = a[:n]
koho = defaultdict(list)
num = sorted(list(set(mae)))
for i in range(n):
  koho[a[i]].append(i)

mn = float("inf")
for k in koho[num[0]]:
  pair = a[k+n]
  mn = min(pair, mn)

if mn <= num[0]:
  print(num[0], mn)
  exit()
import bisect
use = []
last = -1
border = float("inf")
for nn in num:
  if nn < border:
    for k in koho[nn]:
      if k > last:
        use.append(k)
        last = k
    if border == float("inf"):
      border = a[koho[nn][0]+n]
flg = 0
for i in use[1:]:
  if a[i+n] == border: continue
  elif a[i+n] < border: break
  else:
    flg = 1
if flg == 1:
  for nn in koho[border]:
    if nn > last:
      use.append(nn)
ansm = []
ansu = []
for i in use:
  ansm.append(a[i])
  ansu.append(a[i+n])
print(*(ansm+ansu))

  
