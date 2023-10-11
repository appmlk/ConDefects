n,k,*A = map(int,open(0).read().split())
f = 1
x = 0
for d in range(50):
  if k>>d&1:
    g = 0
    for a in A:
      g ^= f<<a
    f = g
  g = 0
  for i in range(f.bit_length()):
    if i&1:
      x ^= (f>>i&1)<<d
    g ^= (f>>i&1)<<(i>>1)
  f = g
print(x)