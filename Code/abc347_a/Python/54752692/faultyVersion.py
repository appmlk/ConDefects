n, k = map(int, input().split())
a = list(map(int, input().split()))

for i in range(n):
  if a[i]%k==0:
    print(a[i]/k, end=" ")