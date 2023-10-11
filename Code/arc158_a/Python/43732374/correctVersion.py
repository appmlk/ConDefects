t=int(input())
for i in range(t):
  a,b,c=map(int,input().split())
  p=(a+b+c)/3
  if p.is_integer() and (a-b)%2==0 and (b-c)%2==0 and (c-a)%2==0:
    d=(abs(a-p)+abs(b-p)+abs(c-p))//4
    print(int(d))
  else:
    print(-1)