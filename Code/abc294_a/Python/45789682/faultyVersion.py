n = int(input())
a = list(map(int,input().split()))
x = []
for i in range(n):
  if a[i] % 2 ==0:
    x.append(a[i])
print(x)