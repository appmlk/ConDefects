n,d = map(int,input().split())
l = []
l =list(map(int,input().split()))
s = 0
h = 0

for i in range(n-1):
  s = l[i+1]-l[i]
  if s < d:
    print(l[i+1])
    h += 1
    break

if h == 0:
  print("-1")
    