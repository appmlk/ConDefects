a,b=map(int,input().split())
c=[]
for i in range(a):
    c.append(str(input()))
c=c[0:b]
c.sort()
for i in c:
  print(i)