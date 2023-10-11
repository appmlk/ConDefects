x, k = map(int, input().split())

for i in range(k):
  p, q = divmod(x, 10**(i+1))
  if q >= 5:
    p += 1
  x = p*10**(i+1)
print(x)
