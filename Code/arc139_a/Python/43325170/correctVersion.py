
n = int(input())
t = list(map(int, input().split()))

p = 2**t[0]
for i in range(1, n):
  d = 2**t[i]
  w = d
  if w <= p:
    kz = (p-w+d)//d
    w += kz*d
  if w%(d*2)==0:
    w += d
  p = w

print(p)
