n = int(input())
a = list(map(int,input().split()))
odd = []
even = []

for i in range(n):
  if a[i] % 2 == 0:
    even.append(a[i])
  else:
    odd.append(a[i])

if len(odd) <= 1 and len(even) <= 1:
  print(-1)
elif len(odd) <= 1:
  print(even[-1] + even[-2])
elif len(even) <= 1:
  print(odd[-1] + odd[-2])
else:
  print(max(even[-1] + even[-2], odd[-1] + odd[-2]))