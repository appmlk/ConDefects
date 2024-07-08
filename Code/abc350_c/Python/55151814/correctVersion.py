n = int(input())
a = list(map(int,input().split()))
for i in range(n):
  a[i] -= 1

ans = []
for i in range(n):
  while a[i] != i:
    now = a[i]
    ans.append([i+1,a[i]+1])
    a[i], a[now] = a[now], a[i]
    
print(len(ans))
for x in ans:
  print(*sorted(x))