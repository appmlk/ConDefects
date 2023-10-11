t=int(input())
for i in range(t):
  a,b,c=map(int,input().split())
  p=(a+b+c)/3
  if p.is_integer():
    d=(abs(a-p)+abs(b-p)+abs(c-p))//4
    print(int(d))
  else:
    print(-1)