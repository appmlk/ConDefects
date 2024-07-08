n, h, x = map(int, input().split())
p = list(map(int, input().split()))

for i in range(n):
  if x <= p[i]+h:
    print(i+1)
    break