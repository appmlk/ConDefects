n,d = map(int,input().split())
t = list(map(int,input().split()))
ret = -1
p = -d-1
for i in t:
  if i-p<=d:
    ret = i
    break
  p=i
print(ret)