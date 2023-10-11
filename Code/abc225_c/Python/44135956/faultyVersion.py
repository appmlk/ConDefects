from collections import defaultdict
n, m = map(int, input().split())
b0 = list(map(int, input().split()))
ans = 'Yes'

for i in range(m-1):
  if b0[i+1] - b0[i] != 1:
    ans = 'No'

for i in range(n-1):
  b = list(map(int, input().split()))
  for i in range(m):
    if b[i] - b0[i] != 7:
      ans = 'No'
  b0 = b

print(ans)