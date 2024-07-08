n, s, m, l = map(int,input().split())
sum = 10**10

for j in range(20):
  for k in range(15):
    for a in range(10):
      if n <= 6*j + 8*k + 12* a <= n + 11:
        if s*j + m*k + l*a < sum:
          sum = s*j + m*k + l*a
          
print(sum)