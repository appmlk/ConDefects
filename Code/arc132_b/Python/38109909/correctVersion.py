n = int(input())

a = list(map(int, input().split()))

b = [a[0]]

for i in range(n-1):
 if abs(a[i+1]-a[i]) != 1:
  break
 b.append(a[i+1])

if b == [1]:
 print(2)
elif b[1] > b[0]:
 if len(b) == n:
  print(0)
 else:
  print(min(len(b),n-len(b)+2))
else:
 if len(b) == n:
  print(1)
 else:
  print(min(len(b)+1,n-len(b)+1))


