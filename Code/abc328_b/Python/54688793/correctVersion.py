N = int(input())
D = list(map(int,input().split()))
ans = 0
for x in range(1,N+1):
  for y in range(1,D[x-1]+1):
    x = str(x)
    a = list(x)
    a = list(map(int,a))
    y = str(y)
    b = list(y)
    b = list(map(int,b))
    a = set(a)
    b = set(b)
    a = list(a)
    b = list(b)
    #print(a,b)
    a = list(a)
    b = list(b)
    if len(a) == 1 and len(b) == 1 and a == b:
      ans += 1
print(ans)
  