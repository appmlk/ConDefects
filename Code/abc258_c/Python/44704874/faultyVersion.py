n,q = map(int,input().split())
s = str(input())
cnt = 0

for i in range(q):
  t,x = map(int,input().split())
  if t == 1:
    cnt += 1
    cnt = cnt % n
  if t == 2:
    print(s[(n - cnt + (x-1))%n])