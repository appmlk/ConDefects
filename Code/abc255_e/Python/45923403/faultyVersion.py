from collections import defaultdict


n, m = map(int, input().split())
s = list(map(int, input().split()))
scc = [s[0]]
for i in range(1, n-1):
  scc.append(scc[-1]+ (-1)**(i)*s[i])
x = list(map(int, input().split()))
x = set(x)

d = defaultdict(int)
for i in range(1, n):
  for j in x:
    tmp = scc[i-1] + (-1)**(i)*j
    d[tmp] += 1

ans = 0
for k, v in d.items():
  ans = max(ans, v+v*(k in x))

print(ans)
