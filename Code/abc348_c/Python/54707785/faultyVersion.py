n = int(input())
cs = {}
for i in range(n):
  a, c = map(int, input().split())
  if c in cs:
    cs[c].append(a)
  else:
    cs[c] = [a]

keys = cs.keys()
c = -1
a = 0
for k in keys:
  m = min(cs[k])
  if m > a:
    c = k
    a = m
print(c)