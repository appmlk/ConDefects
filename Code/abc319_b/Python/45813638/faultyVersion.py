n = int(input())
s = ["-"] * (n+1)

for i in range(n+1):
  for j in range(1, 9):
    if n % j == 0 and i % (n/j) == 0:
      s[i] = str(j)
      break

result = ''.join(s)
print(result)
